package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.FutureEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueFutureEvent {
    @TargetAggregateIdentifier
    final String id;
    final FutureEntity future;

    public IssueFutureEvent(String id, FutureEntity future) {
        this.id = id;
        this.future = future;
    }

    public String getId() {
        return id;
    }

    public FutureEntity getFutureDTO() {
        return future;
    }
}
