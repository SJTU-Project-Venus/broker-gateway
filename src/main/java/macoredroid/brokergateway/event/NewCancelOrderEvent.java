package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Domain.CancelOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewCancelOrderEvent {
    @TargetAggregateIdentifier
    final String id;
    final CancelOrder cancelOrder;

    public String getId() {
        return id;
    }

    public CancelOrder getCancelOrder() {
        return cancelOrder;
    }

    public NewCancelOrderEvent(String id, CancelOrder cancelOrder) {
        this.id = id;
        this.cancelOrder = cancelOrder;
    }
}
