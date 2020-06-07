package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueMarketOrderEvent;
import macoredroid.brokergateway.event.MarketOrderCancelledEvent;
import macoredroid.brokergateway.event.MarketOrderCountDecreasedEvent;
import macoredroid.brokergateway.event.StopOrderToMarketOrderConvertedEvent;
import macoredroid.brokergateway.Entity.MarketOrderEntity;
import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.repository.MarketOrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MarketOrderListener {
    @Autowired
    MarketOrderRepository marketOrderRepository;

    @EventHandler
    public void on(IssueMarketOrderEvent issueMarketOrderEvent){
        MarketOrderEntity marketOrderEntity = issueMarketOrderEvent.getMarketOrderEntity();
        marketOrderRepository.save(marketOrderEntity);
    }

    @EventHandler
    public void on(MarketOrderCountDecreasedEvent marketOrderCountDecreasedEvent){
        MarketOrderEntity marketOrderEntity = marketOrderCountDecreasedEvent.getMarketOrderEntity();
        marketOrderEntity.setStatusSwitchTime(DateUtil.getDate(new Date()));
        marketOrderRepository.save(marketOrderEntity);
    }

    @EventHandler
    public void on(MarketOrderCancelledEvent marketOrderCancelledEvent){
        MarketOrderEntity marketOrderEntity = marketOrderRepository.findMarketOrderDTOById(marketOrderCancelledEvent.getMarketOrderId());
        marketOrderEntity.setStatus(Status.CANCELLED);
        marketOrderEntity.setStatusSwitchTime(DateUtil.getDate(new Date()));
        marketOrderRepository.save(marketOrderEntity);
    }

    @EventHandler
    public void on(StopOrderToMarketOrderConvertedEvent stopOrderToMarketOrderConvertedEvent){
        MarketOrderEntity marketOrderEntity = stopOrderToMarketOrderConvertedEvent.getMarketOrderEntity();
        marketOrderRepository.save(marketOrderEntity);
    }
}
