package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.LimitOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueLimitOrderEvent{
    @TargetAggregateIdentifier
    final String id;
    final LimitOrderEntity limitOrderEntity;

    public IssueLimitOrderEvent(String id, LimitOrderEntity limitOrderEntity) {
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
