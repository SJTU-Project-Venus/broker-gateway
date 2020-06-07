package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.LimitOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewLimitOrderEvent {
    @TargetAggregateIdentifier
    final String id;
    final LimitOrderEntity limitOrderEntity;

    public NewLimitOrderEvent(String id, LimitOrderEntity limitOrderEntity) {
        this.id = id;
        this.limitOrderEntity = limitOrderEntity;
    }

    public LimitOrderEntity getLimitOrderEntity() {
        return limitOrderEntity;
    }

    public String getId() {
        return id;
    }
}
