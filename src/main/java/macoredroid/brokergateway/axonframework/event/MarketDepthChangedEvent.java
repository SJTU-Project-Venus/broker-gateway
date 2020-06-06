package macoredroid.brokergateway.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthChangedEvent {
    @TargetAggregateIdentifier
    final String id;

    public MarketDepthChangedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
