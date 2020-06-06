package macoredroid.brokergateway.axonframework.command;

import macoredroid.brokergateway.DTO.FutureDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueFutureCommand {
    @TargetAggregateIdentifier
    final String id;
    final FutureDTO future;

    public IssueFutureCommand(String id, FutureDTO future) {
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
