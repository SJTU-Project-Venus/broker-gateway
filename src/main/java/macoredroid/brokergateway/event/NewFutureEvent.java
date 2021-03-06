package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.FutureEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewFutureEvent {
    @TargetAggregateIdentifier
    final String id;
    final FutureEntity future;

    public NewFutureEvent(String id, FutureEntity future) {
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
