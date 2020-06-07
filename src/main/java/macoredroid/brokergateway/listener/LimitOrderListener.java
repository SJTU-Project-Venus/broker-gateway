package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueLimitOrderEvent;
import macoredroid.brokergateway.event.LimitOrderCancelledEvent;
import macoredroid.brokergateway.event.LimitOrderCountDecreasedEvent;
import macoredroid.brokergateway.event.StopOrderToLimitOrderConvertedEvent;
import macoredroid.brokergateway.Entity.LimitOrderEntity;
import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.repository.LimitOrderRepository;
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
    LimitOrderRepository limitOrderRepository;

    @EventHandler
    public void on(IssueLimitOrderEvent limitOrderEvent){
        //logger.info("LimitOrder saved into repository");
        LimitOrderEntity limitOrderEntity = limitOrderEvent.getLimitOrderEntity();
        limitOrderRepository.save(limitOrderEntity);
    }

    @EventHandler
    public void on(LimitOrderCountDecreasedEvent limitOrderCountDecreasedEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderEntity limitOrderEntity = limitOrderCountDecreasedEvent.getLimitOrderEntity();
        limitOrderEntity.setStatusSwitchTime(DateUtil.getDate(new Date()));
        limitOrderRepository.save(limitOrderEntity);
    }

    @EventHandler
    public void on(LimitOrderCancelledEvent limitOrderCancelledEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderEntity limitOrderEntity = limitOrderRepository.findLimitOrderDTOById(limitOrderCancelledEvent.getLimitOrderId());
        limitOrderEntity.setStatusSwitchTime(DateUtil.getDate(new Date()));
        limitOrderEntity.setStatus(Status.CANCELLED);
        limitOrderRepository.save(limitOrderEntity);
    }

    @EventHandler
    public void on (StopOrderToLimitOrderConvertedEvent stopOrderToLimitOrderConvertedEvent){
        LimitOrderEntity limitOrderEntity = stopOrderToLimitOrderConvertedEvent.getLimitOrderEntity();
        limitOrderRepository.save(limitOrderEntity);
    }
}
