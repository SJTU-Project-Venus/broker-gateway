package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueOrderBlotterEvent;
import macoredroid.brokergateway.repository.OrderBlotterDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBlotterListener {
    @Autowired
    OrderBlotterDTORepository orderBlotterDTORepository;

    @EventHandler
    public void on(IssueOrderBlotterEvent issueOrderBlotterEvent){
        orderBlotterDTORepository.save(issueOrderBlotterEvent.getOrderBlotterEntity());
    }
}
