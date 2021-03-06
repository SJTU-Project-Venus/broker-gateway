package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.CancelOrderFinishedEvent;
import macoredroid.brokergateway.event.NewCancelOrderEvent;
import macoredroid.brokergateway.Domain.CancelOrder;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.repository.CancelOrderRepository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CancelOrderListener {
    @Autowired
    CancelOrderRepository cancelOrderRepository;

    @EventSourcingHandler
    public void on(NewCancelOrderEvent newCancelOrderEvent){
        cancelOrderRepository.save(newCancelOrderEvent.getCancelOrder());
    }

    @EventSourcingHandler
    public void on(CancelOrderFinishedEvent cancelOrderFinishedEvent){
        CancelOrder cancelOrder = cancelOrderRepository.findCancelOrderById(cancelOrderFinishedEvent.getCancelOrderId());
        cancelOrder.setStatus(cancelOrderFinishedEvent.getTargetStatus());
        cancelOrder.setStatusSwitchTime(DateUtil.getDate(new Date()));
        cancelOrderRepository.save(cancelOrder);
    }
}
