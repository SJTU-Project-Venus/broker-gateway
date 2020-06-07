package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.OrderBlotterEntity;

public class IssueOrderBlotterEvent {
    final String id;
    final OrderBlotterEntity orderBlotterEntity;

    public IssueOrderBlotterEvent(String id, OrderBlotterEntity orderBlotterEntity) {
        this.id = id;
        this.orderBlotterEntity = orderBlotterEntity;
    }

    public OrderBlotterEntity getOrderBlotterEntity() {
        return orderBlotterEntity;
    }
}
