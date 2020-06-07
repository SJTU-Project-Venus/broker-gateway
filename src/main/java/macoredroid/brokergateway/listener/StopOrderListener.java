package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueStopOrderEvent;
import macoredroid.brokergateway.event.StopOrderCancelledEvent;
import macoredroid.brokergateway.event.StopOrderConvertedEvent;
import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.Entity.StopOrderEntity;
import macoredroid.brokergateway.Util;
import macoredroid.brokergateway.repository.StopOrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StopOrderListener {
    Logger logger = LoggerFactory.getLogger(StopOrderEntity.class);
    @Autowired
    StopOrderRepository stopOrderRepository;

    @EventHandler
    public void on(IssueStopOrderEvent issueStopOrderEvent){
        stopOrderRepository.save(issueStopOrderEvent.getStopOrderEntity());
    }

    @EventHandler
    public void on(StopOrderConvertedEvent stopOrderConvertedEvent){
        //logger.info("in StopOrderConvertedEvent");
        String id = stopOrderConvertedEvent.getStopOrderId();
        StopOrderEntity stopOrderEntity = stopOrderRepository.findStopOrderById(id);
        stopOrderEntity.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrderEntity.setStatus(Status.FINISHED);
        stopOrderRepository.save(stopOrderEntity);
    }

    @EventHandler
    public void on(StopOrderCancelledEvent stopOrderCancelledEvent){
        String id = stopOrderCancelledEvent.getStopOrderId();
        StopOrderEntity stopOrderEntity = stopOrderRepository.findStopOrderById(id);
        stopOrderEntity.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrderEntity.setStatus(Status.CANCELLED);
        stopOrderRepository.save(stopOrderEntity);
    }
}
