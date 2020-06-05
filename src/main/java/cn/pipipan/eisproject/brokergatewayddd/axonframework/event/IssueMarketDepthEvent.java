package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketDepthEvent {
    @TargetAggregateIdentifier
    final String id;

    public String getId() {
        return id;
    }

    public IssueMarketDepthEvent(String id) {
        this.id = id;
    }
}
