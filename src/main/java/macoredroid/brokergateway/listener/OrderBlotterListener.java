package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueOrderBlotterEvent;
import macoredroid.brokergateway.repository.OrderBlotterRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBlotterListener {
    @Autowired
    OrderBlotterRepository orderBlotterRepository;

    @EventHandler
    public void on(IssueOrderBlotterEvent issueOrderBlotterEvent){
        orderBlotterRepository.save(issueOrderBlotterEvent.getOrderBlotterEntity());
    }
}
