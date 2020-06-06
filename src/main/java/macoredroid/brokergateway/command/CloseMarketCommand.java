package macoredroid.brokergateway.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CloseMarketCommand {
    @TargetAggregateIdentifier
    final String id;

    public CloseMarketCommand(String id) {
        this.id = id;
    }
}
