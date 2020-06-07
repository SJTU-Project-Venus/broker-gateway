package macoredroid.brokergateway.command;

import macoredroid.brokergateway.Entity.LimitOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewLimitOrderCommand {
    @TargetAggregateIdentifier
    final private String id;
    final private LimitOrderEntity limitOrderEntity;

    public NewLimitOrderCommand(String id, LimitOrderEntity limitOrderEntity) {
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
