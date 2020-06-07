package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.OrderBlotterEntity;

public class NewOrderBlotterEvent {
    final String id;
    final OrderBlotterEntity orderBlotterEntity;

    public NewOrderBlotterEvent(String id, OrderBlotterEntity orderBlotterEntity) {
        this.id = id;
        this.orderBlotterEntity = orderBlotterEntity;
    }

    public OrderBlotterEntity getOrderBlotterEntity() {
        return orderBlotterEntity;
    }
}
