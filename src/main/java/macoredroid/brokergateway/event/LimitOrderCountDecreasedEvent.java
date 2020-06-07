package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.LimitOrderEntity;

public class LimitOrderCountDecreasedEvent {
    final String id;
    final LimitOrderEntity limitOrderEntity;

    public LimitOrderCountDecreasedEvent(String id, LimitOrderEntity limitOrderEntity) {
        this.id = id;
        this.limitOrderEntity = limitOrderEntity;
    }

    public LimitOrderEntity getLimitOrderEntity(){
        return this.limitOrderEntity;
    }
}
