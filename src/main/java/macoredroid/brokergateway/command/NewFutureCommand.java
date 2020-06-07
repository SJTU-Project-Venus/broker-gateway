package macoredroid.brokergateway.command;

import macoredroid.brokergateway.Entity.FutureEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewFutureCommand {
    @TargetAggregateIdentifier
    final String id;
    final FutureEntity future;

    public NewFutureCommand(String id, FutureEntity future) {
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
