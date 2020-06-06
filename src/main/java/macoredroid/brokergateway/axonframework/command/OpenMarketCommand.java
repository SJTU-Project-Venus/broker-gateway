package macoredroid.brokergateway.axonframework.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class OpenMarketCommand {
    @TargetAggregateIdentifier
    final String id;

    public OpenMarketCommand(String id) {
        this.id = id;
    }
}
