package macoredroid.brokergateway.core;

import lombok.Data;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.Domain.*;
import macoredroid.brokergateway.Entity.MarketDepthEntity;
import macoredroid.brokergateway.Entity.OrderBlotterEntity;
import macoredroid.brokergateway.Entity.StopOrderEntity;
import macoredroid.brokergateway.EntityConvert;
import macoredroid.brokergateway.command.NewCancelOrderCommand;
import macoredroid.brokergateway.command.NewLimitOrderCommand;
import macoredroid.brokergateway.command.NewMarketOrderCommand;
import macoredroid.brokergateway.command.NewStopOrderCommand;
import macoredroid.brokergateway.event.*;
import macoredroid.brokergateway.model.Status;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Aggregate
public class MarketDepth {
    Logger logger = LoggerFactory.getLogger(MarketDepth.class);
    @Autowired
    CommandGateway commandGateway;

    class Convert implements EntityConvert<MarketDepth, MarketDepthEntity> {
        @Override
        public MarketDepthEntity convertFrom(MarketDepth marketDepth) {
            MarketDepthEntity marketDepthEntity = new MarketDepthEntity();
            marketDepthEntity.setId(marketDepth.id);
            for (int i = marketDepth.buyers.size() - 1; i >= 0; i--) {
                OrderPriceComposite orderPriceComposite = buyers.get(i);
                marketDepthEntity.addBuyer(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice());
            }
            for (int i = 0; i < marketDepth.sellers.size(); i++) {
                OrderPriceComposite orderPriceComposite = sellers.get(i);
                marketDepthEntity.addSeller(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice());
            }
            return marketDepthEntity;
        }
    }

    public MarketDepthEntity convertToMarketDepthDTO() {
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    @Data
    private static class OrderPriceComposite {
        int price;
        List<LimitOrder> limitOrders;

        OrderPriceComposite() {
        }

        OrderPriceComposite(LimitOrder limitOrder) {
            this.price = limitOrder.getUnitPrice();
            this.limitOrders = new ArrayList<>();
            this.limitOrders.add(limitOrder);
        }

        void addOrder(LimitOrder order) {
            this.limitOrders.add(order);
        }
    }

    

    @AggregateIdentifier
    private String id;

    private List<OrderPriceComposite> sellers;
    private List<OrderPriceComposite> buyers;
    private List<MarketOrder> marketOrders;
    private List<StopOrderEntity> stopOrderEntities;
    private MarketPrice marketPrice;

    private LimitOrder getFirstBuyer(){
        if (buyers.size() == 0) return new NoBuyerLimitOrder();
        return buyers.get(buyers.size() - 1).getLimitOrders().get(0);
    }

    private LimitOrder getFirstSeller(){
        if (sellers.size() == 0) return new NoSellerLimitOrder();
        return sellers.get(0).getLimitOrders().get(0);
    }

    private MarketOrder getFirstMarketOrder() {
        for (MarketOrder marketOrder : marketOrders){
            if ((marketOrder.isBuyer() && !sellers.isEmpty())
                    || (marketOrder.isSeller() && !buyers.isEmpty())) {
                return marketOrder;
            }
        }
        return null;
    }

    private boolean canMarketOrderDone() {
        return getFirstMarketOrder() != null;
    }

    public MarketDepth(String id) {
        AggregateLifecycle.apply(new NewMarketDepthEvent(id));
    }

    @CommandHandler
    public void handle(NewLimitOrderCommand issueLimitOrderCommand){
        AggregateLifecycle.apply(new NewLimitOrderEvent(id, issueLimitOrderCommand.getLimitOrderEntity()));
    }

    @CommandHandler
    public void handle(NewMarketOrderCommand issueMarketOrderCommand){
        AggregateLifecycle.apply(new NewMarketOrderEvent(id, issueMarketOrderCommand.getMarketOrderEntity()));
    }

    @CommandHandler
    public void handle(NewCancelOrderCommand newCancelOrderCommand){
        AggregateLifecycle.apply(new NewCancelOrderEvent(id, newCancelOrderCommand.getCancelOrder()));
    }

    @CommandHandler
    public void handle(NewStopOrderCommand newStopOrderCommand){
        AggregateLifecycle.apply(new NewStopOrderEvent(id, newStopOrderCommand.getStopOrderEntity()));
    }

    @EventSourcingHandler
    public void on(NewMarketDepthEvent newMarketDepthEvent) {
        this.id = newMarketDepthEvent.getId();
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.marketOrders = new ArrayList<>();
        this.stopOrderEntities = new ArrayList<>();
        this.marketPrice = new MarketPrice(DateUtil.getNowDate(), 0, id);
    }

    @EventSourcingHandler
    public void on(NewLimitOrderEvent newLimitOrderEvent){
        LimitOrder limitOrder = newLimitOrderEvent.getLimitOrderEntity().convertToLimitOrder();
        insertIntoWaitingQueue(limitOrder, limitOrder.isBuyer() ? buyers : sellers);
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }

    @EventSourcingHandler
    public void on(NewStopOrderEvent newStopOrderEvent){
        stopOrderEntities.add(newStopOrderEvent.getStopOrderEntity());
        dealWithStopOrders();
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }

    @EventSourcingHandler
    public void on(NewCancelOrderEvent event){
        CancelOrder cancelOrder = event.getCancelOrder();
        Status status = Status.FAILURE;
        switch (cancelOrder.getTargetType()){
            case MarketOrder:
                for (MarketOrder marketOrder : marketOrders){
                    if (marketOrder.getId().equals(cancelOrder.getTargetId())){
                        marketOrders.remove(marketOrder);
                        status = Status.FINISHED;
                        AggregateLifecycle.apply(new MarketOrderCancelledEvent(id, marketOrder.getId()));
                        break;
                    }
                }
                break;
            case LimitOrder:
                List<OrderPriceComposite> waitingComposites = cancelOrder.isBuyer() ? buyers : sellers;
                int index = FindIndex(cancelOrder.getUnitPrice(), waitingComposites);
                if (index == waitingComposites.size() || waitingComposites.get(index).getPrice() != cancelOrder.getUnitPrice())
                    break;
                else {
                    List<LimitOrder> limitOrders = waitingComposites.get(index).getLimitOrders();
                    for (LimitOrder limitOrder : limitOrders){
                        if (limitOrder.getId().equals(cancelOrder.getTargetId())) {
                            limitOrders.remove(limitOrder);
                            if (limitOrders.isEmpty()) waitingComposites.remove(index);
                            status = Status.FINISHED;
                            AggregateLifecycle.apply(new LimitOrderCancelledEvent(id, limitOrder.getId()));
                            break;
                        }
                    }
                }
                break;
            case StopOrder:
                for (StopOrderEntity stopOrderEntity : stopOrderEntities){
                    if (stopOrderEntity.getId().equals(cancelOrder.getTargetId())){
                        stopOrderEntities.remove(stopOrderEntity);
                        status = Status.FINISHED;
                        AggregateLifecycle.apply(new StopOrderCancelledEvent(id, stopOrderEntity.getId()));
                        break;
                    }
                }
                break;
        }
        AggregateLifecycle.apply(new CancelOrderFinishedEvent(id, cancelOrder.getId(), status));
    }

    @EventSourcingHandler
    public void on(NewMarketOrderEvent newMarketOrderEvent){
        MarketOrder marketOrder = newMarketOrderEvent.getMarketOrderEntity().convertToMarketOrder();
        insertIntoMarketOrders(marketOrder);
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }

    private void insertIntoMarketOrders(MarketOrder marketOrder) {
        marketOrders.add(marketOrder);
    }

    private void insertIntoWaitingQueue(LimitOrder order, List<OrderPriceComposite> waitingComposites, int i) {
        if (i == waitingComposites.size() || waitingComposites.get(i).getPrice() != order.getUnitPrice())
            waitingComposites.add(i, new OrderPriceComposite(order));
        else
            waitingComposites.get(i).addOrder(order);
    }

    private void insertIntoWaitingQueue(LimitOrder order, List<OrderPriceComposite> waitingComposites) {
        int index = FindIndex(order.getUnitPrice(), waitingComposites);
        insertIntoWaitingQueue(order, waitingComposites, index);
    }

    private int FindIndex(int price, List<OrderPriceComposite> waitingComposites) {
        for (int i = 0; i < waitingComposites.size(); ++i) {
            if (waitingComposites.get(i).getPrice() >= price) return i;
        }
        return waitingComposites.size();
    }

    @EventSourcingHandler
    public void on(MarketDepthChangedEvent marketDepthChangedEvent) {
        dealWithStopOrders();
        if (isFixed()) AggregateLifecycle.apply(new MarketDepthFixedEvent(id, convertToMarketDepthDTO(), marketPrice.clone()));
        else if (canMarketOrderDone()){
            MarketOrder marketOrder = getFirstMarketOrder();
            LimitOrder limitOrder = marketOrder.isBuyer() ? getFirstSeller() : getFirstBuyer();
            dealWithMarketOrders(marketOrder, limitOrder);
        }
        else {
            LimitOrder buyer_order = getFirstBuyer();
            LimitOrder seller_order = getFirstSeller();
            dealWithLimitOrders(buyer_order, seller_order);
        }
    }

    // cur >= buy_limit or cur <= sell_limit
    private void dealWithStopOrders() {
        int currentPrice = (int) marketPrice.getCurrentPrice();
        int buyerCurrentComparePrice = currentPrice == 0 ? Integer.MIN_VALUE :
                currentPrice;
        int sellerCurrentComparePrice = currentPrice == 0 ? Integer.MAX_VALUE :
                currentPrice;
        Iterator<StopOrderEntity> iterator = stopOrderEntities.iterator();
        while(iterator.hasNext()){
            StopOrderEntity stopOrderEntity = iterator.next();
            if ((stopOrderEntity.isBuyer() && stopOrderEntity.getStopPrice() <= buyerCurrentComparePrice)
                || (stopOrderEntity.isSeller() && stopOrderEntity.getStopPrice() >= sellerCurrentComparePrice)) {
                //logger.info("get the converted order");
                switch (stopOrderEntity.getTargetType()){
                    case LimitOrder:
                        LimitOrder limitOrder = stopOrderEntity.convertToLimitOrder();
                        insertIntoWaitingQueue(limitOrder, limitOrder.isBuyer() ? buyers : sellers);
                        AggregateLifecycle.apply(new StopOrderToLimitOrderConvertedEvent(id, stopOrderEntity.getId(), limitOrder.convertToLimitOrderDTO()));
                        break;
                    case MarketOrder:
                        MarketOrder marketOrder = stopOrderEntity.convertToMarketOrder();
                        insertIntoMarketOrders(marketOrder);
                        AggregateLifecycle.apply(new StopOrderToMarketOrderConvertedEvent(id, stopOrderEntity.getId(), marketOrder.convertToMarketOrderDTO()));
                        break;
                }
                iterator.remove();
            }
        }
    }

    private void dealWithMarketOrders(MarketOrder marketOrder, LimitOrder limitOrder) {
        int delta = Math.min(marketOrder.getCount(), limitOrder.getCount());
        decreaseMarketOrderCount(marketOrder, delta);
        decreaseLimitOrderCount(limitOrder, delta);
        OrderBlotterEntity orderBlotterEntity = marketOrder.isBuyer() ? OrderBlotterEntity.createOrderBlotter(delta, limitOrder.getUnitPrice(), marketOrder.convertToMarketOrderDTO(), limitOrder.convertToLimitOrderDTO(), this.id) :
                OrderBlotterEntity.createOrderBlotter(delta, limitOrder.getUnitPrice(), limitOrder.convertToLimitOrderDTO(), marketOrder.convertToMarketOrderDTO(), this.id);
        marketPrice.update(orderBlotterEntity);
        AggregateLifecycle.apply(new NewOrderBlotterEvent(id, orderBlotterEntity));
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }


    private void dealWithLimitOrders(LimitOrder buyer_order, LimitOrder seller_order) {
        int delta = Math.min(buyer_order.getCount(), seller_order.getCount());
        decreaseLimitOrderCount(buyer_order, delta);
        decreaseLimitOrderCount(seller_order, delta);
        OrderBlotterEntity orderBlotterEntity = OrderBlotterEntity.createOrderBlotter(delta, calculatePrice(buyer_order, seller_order), buyer_order.convertToLimitOrderDTO(), seller_order.convertToLimitOrderDTO(), this.id);
        marketPrice.update(orderBlotterEntity);
        AggregateLifecycle.apply(new NewOrderBlotterEvent(id, orderBlotterEntity));
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }

    private int calculatePrice(LimitOrder buyer_order, LimitOrder seller_order) {
        Date buyer_order_date = DateUtil.parseString(buyer_order.getCreationTime());
        Date seller_order_date = DateUtil.parseString(seller_order.getCreationTime());
        try {
            return buyer_order_date.before(seller_order_date) ? buyer_order.getUnitPrice() : seller_order.getUnitPrice();
        }
        catch (Exception e){
            return buyer_order.getUnitPrice();
        }
    }


    private void decreaseMarketOrderCount(MarketOrder marketOrder, int delta) {
        marketOrder.decreaseCount(delta);
        if (marketOrder.getCount() == 0) marketOrders.remove(marketOrder);
        AggregateLifecycle.apply(new MarketOrderCountDecreasedEvent(id, marketOrder.convertToMarketOrderDTO()));
    }

    private void decreaseLimitOrderCount(LimitOrder limitOrder, int delta) {
        limitOrder.decreaseCount(delta);
        List<OrderPriceComposite> waitingComposites = limitOrder.isBuyer() ? buyers : sellers;
        int index = limitOrder.isBuyer() ? buyers.size() - 1 : 0;
        if (limitOrder.getCount() == 0) {
            List<LimitOrder> limitOrders = waitingComposites.get(index).getLimitOrders();
            limitOrders.remove(0);
            if (limitOrders.isEmpty()) waitingComposites.remove(index);
        }
        AggregateLifecycle.apply(new LimitOrderCountDecreasedEvent(id, limitOrder.convertToLimitOrderDTO()));
    }

    private boolean isFixed() {
        return !canMarketOrderDone() && (buyers.isEmpty() || sellers.isEmpty() || buyers.get(buyers.size() - 1).getPrice() < sellers.get(0).getPrice());
    }

    protected MarketDepth() {

    }
}
