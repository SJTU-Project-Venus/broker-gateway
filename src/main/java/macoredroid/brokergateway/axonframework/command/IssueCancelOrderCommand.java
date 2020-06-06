package macoredroid.brokergateway.axonframework.command;

import macoredroid.brokergateway.DTO.CancelOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueCancelOrderCommand implements IssueOrderCommand{
    @TargetAggregateIdentifier
    final String id;
    final CancelOrder cancelOrder;

    public String getId() {
        return id;
    }

    public CancelOrder getCancelOrder() {
        return cancelOrder;
    }

    public IssueCancelOrderCommand(String id, CancelOrder cancelOrder) {
        this.id = id;
        this.cancelOrder = cancelOrder;
    }
}
