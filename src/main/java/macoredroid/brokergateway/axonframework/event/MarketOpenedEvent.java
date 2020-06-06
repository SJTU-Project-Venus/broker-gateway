package macoredroid.brokergateway.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketOpenedEvent {
    public MarketOpenedEvent(String id) {
        this.id = id;
    }

    @TargetAggregateIdentifier
    String id;
}
