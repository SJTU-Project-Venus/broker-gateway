package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.OrderBlotterDTO;

public class IssueOrderBlotterEvent {
    final String id;
    final OrderBlotterDTO orderBlotterDTO;

    public IssueOrderBlotterEvent(String id, OrderBlotterDTO orderBlotterDTO) {
        this.id = id;
        this.orderBlotterDTO = orderBlotterDTO;
    }

    public OrderBlotterDTO getOrderBlotterDTO() {
        return orderBlotterDTO;
    }
}
