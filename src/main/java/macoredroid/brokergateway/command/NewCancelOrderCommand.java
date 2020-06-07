package macoredroid.brokergateway.command;

import macoredroid.brokergateway.Domain.CancelOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewCancelOrderCommand {
    @TargetAggregateIdentifier
    final String id;
    final CancelOrder cancelOrder;

    public String getId() {
        return id;
    }

    public CancelOrder getCancelOrder() {
        return cancelOrder;
    }

    public NewCancelOrderCommand(String id, CancelOrder cancelOrder) {
        this.id = id;
        this.cancelOrder = cancelOrder;
    }
}
