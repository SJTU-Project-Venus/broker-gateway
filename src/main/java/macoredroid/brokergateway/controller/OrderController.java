package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.Domain.*;
import macoredroid.brokergateway.Entity.LimitOrderEntity;
import macoredroid.brokergateway.Entity.MarketOrderEntity;
import macoredroid.brokergateway.Entity.OrderEntity;
import macoredroid.brokergateway.Entity.StopOrderEntity;
import macoredroid.brokergateway.RealDTO.RealCancelOrder;
import macoredroid.brokergateway.RealDTO.RealLimitOrder;
import macoredroid.brokergateway.RealDTO.RealMarketOrder;
import macoredroid.brokergateway.RealDTO.RealStopOrderDTO;
import macoredroid.brokergateway.command.NewCancelOrderCommand;
import macoredroid.brokergateway.command.NewLimitOrderCommand;
import macoredroid.brokergateway.command.NewMarketOrderCommand;
import macoredroid.brokergateway.command.NewStopOrderCommand;
import macoredroid.brokergateway.Util;
import macoredroid.brokergateway.repository.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class OrderController {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    CommandGateway commandGateway;
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;
    @Autowired
    CancelOrderRepository cancelOrderRepository;
    @Autowired
    StopOrderRepository stopOrderRepository;
    @Autowired
    FutureDTORepository futureDTORepository;

    @PostMapping("/limitOrders")
    public Response<LimitOrderEntity> processLimitOrder(@RequestBody RealLimitOrder ReallimitOrder){
        LimitOrderEntity limitOrderEntity =new LimitOrderEntity();
        BeanUtils.copyProperties(ReallimitOrder, limitOrderEntity);
        completeOrder(limitOrderEntity);
        limitOrderEntity.setCount(limitOrderEntity.getTotalCount());
        try{
            commandGateway.send(new NewLimitOrderCommand(limitOrderEntity.getMarketDepthId(), limitOrderEntity)).get();
            LimitOrderEntity res = limitOrderDTORepository.save(limitOrderEntity);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }


    @PostMapping("/marketOrders")
    public Response<MarketOrderEntity> processMarketOrder(@RequestBody RealMarketOrder realMarketOrder){
        MarketOrderEntity marketOrderEntity =new MarketOrderEntity();
        BeanUtils.copyProperties(realMarketOrder, marketOrderEntity);
        completeOrder(marketOrderEntity);
        marketOrderEntity.setCount(marketOrderEntity.getTotalCount());
        try {
            commandGateway.send(new NewMarketOrderCommand(marketOrderEntity.getMarketDepthId(), marketOrderEntity)).get();
            MarketOrderEntity res = marketOrderDTORepository.save(marketOrderEntity);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/cancelOrders")
    public Response<CancelOrder> processCancelOrder(@RequestBody RealCancelOrder realCancelOrder){
        CancelOrder cancelOrder=new CancelOrder();
        BeanUtils.copyProperties(realCancelOrder,cancelOrder);
        completeOrder(cancelOrder);
        try{
            commandGateway.send(new NewCancelOrderCommand(cancelOrder.getMarketDepthId(), cancelOrder)).get();
            CancelOrder res = cancelOrderRepository.save(cancelOrder);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/stopOrders")
    public Response<StopOrderEntity> processStopOrder(@RequestBody RealStopOrderDTO realStopOrderDTO){
        StopOrderEntity stopOrderEntity =new StopOrderEntity();
        BeanUtils.copyProperties(realStopOrderDTO, stopOrderEntity);
        completeOrder(stopOrderEntity);
        try{
            commandGateway.send(new NewStopOrderCommand(stopOrderEntity.getMarketDepthId(), stopOrderEntity)).get();
            StopOrderEntity res = stopOrderRepository.save(stopOrderEntity);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    private void completeOrder(OrderEntity orderEntity) {
        String id = UUID.randomUUID().toString();
        orderEntity.setId(id);
        String creationTime = Util.getDate(new Date());
        orderEntity.setCreationTime(creationTime);
        orderEntity.setStatus(Status.WAITING);
        orderEntity.setMarketDepthId( futureDTORepository.findFutureDTOByNameEquals(orderEntity.getFutureName()).getMarketDepthId());

    }
}
