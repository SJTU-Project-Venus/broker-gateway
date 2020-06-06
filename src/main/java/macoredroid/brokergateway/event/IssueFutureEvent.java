package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Domain.FutureDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueFutureEvent {
    @TargetAggregateIdentifier
    final String id;
    final FutureDTO future;

    public IssueFutureEvent(String id, FutureDTO future) {
        this.id = id;
        this.future = future;
    }

    public String getId() {
        return id;
    }

    public FutureDTO getFutureDTO() {
        return future;
    }
}
