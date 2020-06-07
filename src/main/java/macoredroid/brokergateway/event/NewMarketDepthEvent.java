package macoredroid.brokergateway.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewMarketDepthEvent {
    @TargetAggregateIdentifier
    final String id;

    public String getId() {
        return id;
    }

    public NewMarketDepthEvent(String id) {
        this.id = id;
    }
}
