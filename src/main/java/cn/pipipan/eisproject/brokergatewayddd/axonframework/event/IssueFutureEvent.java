package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
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
