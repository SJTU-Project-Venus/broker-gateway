package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.DTO.*;
import macoredroid.brokergateway.RealDTO.RealCancelOrder;
import macoredroid.brokergateway.RealDTO.RealLimitOrder;
import macoredroid.brokergateway.RealDTO.RealMarketOrder;
import macoredroid.brokergateway.RealDTO.RealStopOrderDTO;
import macoredroid.brokergateway.axonframework.command.IssueCancelOrderCommand;
import macoredroid.brokergateway.axonframework.command.IssueLimitOrderCommand;
import macoredroid.brokergateway.axonframework.command.IssueMarketOrderCommand;
import macoredroid.brokergateway.axonframework.command.IssueStopOrderCommand;
import macoredroid.brokergateway.helper.Util;
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
    public Response<LimitOrderDTO> processLimitOrder(@RequestBody RealLimitOrder ReallimitOrder){
        LimitOrderDTO limitOrderDTO=new LimitOrderDTO();
        BeanUtils.copyProperties(ReallimitOrder, limitOrderDTO);
        completeOrder(limitOrderDTO);
        limitOrderDTO.setCount(limitOrderDTO.getTotalCount());
        try{
            commandGateway.send(new IssueLimitOrderCommand(limitOrderDTO.getMarketDepthId(), limitOrderDTO)).get();
            LimitOrderDTO res = limitOrderDTORepository.save(limitOrderDTO);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }


    @PostMapping("/marketOrders")
    public Response<MarketOrderDTO> processMarketOrder(@RequestBody RealMarketOrder realMarketOrder){
        MarketOrderDTO marketOrderDTO=new MarketOrderDTO();
        BeanUtils.copyProperties(realMarketOrder, marketOrderDTO);
        completeOrder(marketOrderDTO);
        marketOrderDTO.setCount(marketOrderDTO.getTotalCount());
        try {
            commandGateway.send(new IssueMarketOrderCommand(marketOrderDTO.getMarketDepthId(), marketOrderDTO)).get();
            MarketOrderDTO res = marketOrderDTORepository.save(marketOrderDTO);
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
            commandGateway.send(new IssueCancelOrderCommand(cancelOrder.getMarketDepthId(), cancelOrder)).get();
            CancelOrder res = cancelOrderRepository.save(cancelOrder);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/stopOrders")
    public Response<StopOrder> processStopOrder(@RequestBody RealStopOrderDTO realStopOrderDTO){
        StopOrder stopOrder=new StopOrder();
        BeanUtils.copyProperties(realStopOrderDTO, stopOrder);
        completeOrder(stopOrder);
        try{
            commandGateway.send(new IssueStopOrderCommand(stopOrder.getMarketDepthId(), stopOrder)).get();
            StopOrder res = stopOrderRepository.save(stopOrder);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    private void completeOrder(OrderDTO orderDTO) {
        String id = UUID.randomUUID().toString();
        orderDTO.setId(id);
        String creationTime = Util.getDate(new Date());
        orderDTO.setCreationTime(creationTime);
        orderDTO.setStatus(Status.WAITING);
        orderDTO.setMarketDepthId( futureDTORepository.findFutureDTOByNameEquals(orderDTO.getFutureName()).getMarketDepthId());

    }
}
