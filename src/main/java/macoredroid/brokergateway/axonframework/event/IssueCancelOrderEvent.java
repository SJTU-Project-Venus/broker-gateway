package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.CancelOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueCancelOrderEvent {
    @TargetAggregateIdentifier
    final String id;
    final CancelOrder cancelOrder;

    public String getId() {
        return id;
    }

    public CancelOrder getCancelOrder() {
        return cancelOrder;
    }

    public IssueCancelOrderEvent(String id, CancelOrder cancelOrder) {
        this.id = id;
        this.cancelOrder = cancelOrder;
    }
}
