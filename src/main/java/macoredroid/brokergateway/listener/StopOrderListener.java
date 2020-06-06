package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueStopOrderEvent;
import macoredroid.brokergateway.event.StopOrderCancelledEvent;
import macoredroid.brokergateway.event.StopOrderConvertedEvent;
import macoredroid.brokergateway.Domain.Status;
import macoredroid.brokergateway.Domain.StopOrder;
import macoredroid.brokergateway.helper.Util;
import macoredroid.brokergateway.repository.StopOrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StopOrderListener {
    Logger logger = LoggerFactory.getLogger(StopOrder.class);
    @Autowired
    StopOrderRepository stopOrderRepository;

    @EventHandler
    public void on(IssueStopOrderEvent issueStopOrderEvent){
        stopOrderRepository.save(issueStopOrderEvent.getStopOrder());
    }

    @EventHandler
    public void on(StopOrderConvertedEvent stopOrderConvertedEvent){
        //logger.info("in StopOrderConvertedEvent");
        String id = stopOrderConvertedEvent.getStopOrderId();
        StopOrder stopOrder = stopOrderRepository.findStopOrderById(id);
        stopOrder.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrder.setStatus(Status.FINISHED);
        stopOrderRepository.save(stopOrder);
    }

    @EventHandler
    public void on(StopOrderCancelledEvent stopOrderCancelledEvent){
        String id = stopOrderCancelledEvent.getStopOrderId();
        StopOrder stopOrder = stopOrderRepository.findStopOrderById(id);
        stopOrder.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrder.setStatus(Status.CANCELLED);
        stopOrderRepository.save(stopOrder);
    }
}
