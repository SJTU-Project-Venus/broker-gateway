package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueLimitOrderEvent;
import macoredroid.brokergateway.event.LimitOrderCancelledEvent;
import macoredroid.brokergateway.event.LimitOrderCountDecreasedEvent;
import macoredroid.brokergateway.event.StopOrderToLimitOrderConvertedEvent;
import macoredroid.brokergateway.Domain.LimitOrderDTO;
import macoredroid.brokergateway.Domain.Status;
import macoredroid.brokergateway.helper.Util;
import macoredroid.brokergateway.repository.LimitOrderDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LimitOrderListener {
    Logger logger = LoggerFactory.getLogger(LimitOrderListener.class);
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;

    @EventHandler
    public void on(IssueLimitOrderEvent limitOrderEvent){
        //logger.info("LimitOrder saved into repository");
        LimitOrderDTO limitOrderDTO = limitOrderEvent.getLimitOrderDTO();
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on(LimitOrderCountDecreasedEvent limitOrderCountDecreasedEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderDTO limitOrderDTO = limitOrderCountDecreasedEvent.getLimitOrderDTO();
        limitOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on(LimitOrderCancelledEvent limitOrderCancelledEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderDTO limitOrderDTO = limitOrderDTORepository.findLimitOrderDTOById(limitOrderCancelledEvent.getLimitOrderId());
        limitOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        limitOrderDTO.setStatus(Status.CANCELLED);
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on (StopOrderToLimitOrderConvertedEvent stopOrderToLimitOrderConvertedEvent){
        LimitOrderDTO limitOrderDTO = stopOrderToLimitOrderConvertedEvent.getLimitOrderDTO();
        limitOrderDTORepository.save(limitOrderDTO);
    }
}
