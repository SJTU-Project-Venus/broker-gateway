package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.Domain.*;
import macoredroid.brokergateway.Entity.LimitOrderEntity;
import macoredroid.brokergateway.Entity.MarketOrderEntity;
import macoredroid.brokergateway.Entity.OrderEntity;
import macoredroid.brokergateway.Entity.StopOrderEntity;
import macoredroid.brokergateway.DTO.CancelOrderDTO;
import macoredroid.brokergateway.DTO.LimitOrderDTO;
import macoredroid.brokergateway.DTO.MarketOrderDTO;
import macoredroid.brokergateway.DTO.StopOrderDTO;
import macoredroid.brokergateway.command.NewCancelOrderCommand;
import macoredroid.brokergateway.command.NewLimitOrderCommand;
import macoredroid.brokergateway.command.NewMarketOrderCommand;
import macoredroid.brokergateway.command.NewStopOrderCommand;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.model.Response;
import macoredroid.brokergateway.model.Status;
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
    LimitOrderRepository limitOrderRepository;
    @Autowired
    MarketOrderRepository marketOrderRepository;
    @Autowired
    CancelOrderRepository cancelOrderRepository;
    @Autowired
    StopOrderRepository stopOrderRepository;
    @Autowired
    FutureRepository futureRepository;

    @PostMapping("/limitOrders")
    public Response<LimitOrderEntity> processLimitOrder(@RequestBody LimitOrderDTO reallimitOrderDTO){
        LimitOrderEntity limitOrderEntity =new LimitOrderEntity();
        BeanUtils.copyProperties(reallimitOrderDTO, limitOrderEntity);
        completeOrder(limitOrderEntity);
        limitOrderEntity.setCount(limitOrderEntity.getTotalCount());
        try{
            commandGateway.send(new NewLimitOrderCommand(limitOrderEntity.getMarketDepthId(), limitOrderEntity)).get();
            LimitOrderEntity res = limitOrderRepository.save(limitOrderEntity);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }


    @PostMapping("/marketOrders")
    public Response<MarketOrderEntity> processMarketOrder(@RequestBody MarketOrderDTO marketOrderDTO){
        MarketOrderEntity marketOrderEntity =new MarketOrderEntity();
        BeanUtils.copyProperties(marketOrderDTO, marketOrderEntity);
        completeOrder(marketOrderEntity);
        marketOrderEntity.setCount(marketOrderEntity.getTotalCount());
        try {
            commandGateway.send(new NewMarketOrderCommand(marketOrderEntity.getMarketDepthId(), marketOrderEntity)).get();
            MarketOrderEntity res = marketOrderRepository.save(marketOrderEntity);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/cancelOrders")
    public Response<CancelOrder> processCancelOrder(@RequestBody CancelOrderDTO cancelOrderDTO){
        CancelOrder cancelOrder=new CancelOrder();
        BeanUtils.copyProperties(cancelOrderDTO,cancelOrder);
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
    public Response<StopOrderEntity> processStopOrder(@RequestBody StopOrderDTO stopOrderDTO){
        StopOrderEntity stopOrderEntity =new StopOrderEntity();
        BeanUtils.copyProperties(stopOrderDTO, stopOrderEntity);
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
        String creationTime = DateUtil.getDate(new Date());
        orderEntity.setCreationTime(creationTime);
        orderEntity.setStatus(Status.WAITING);
        orderEntity.setMarketDepthId( futureRepository.findFutureDTOByNameEquals(orderEntity.getFutureName()).getMarketDepthId());

    }
}
