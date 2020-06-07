package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.StopOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueStopOrderEvent {
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrderEntity stopOrderEntity;

    public IssueStopOrderEvent(String marketDepthId, StopOrderEntity stopOrderEntity) {
        this.marketDepthId = marketDepthId;
        this.stopOrderEntity = stopOrderEntity;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public StopOrderEntity getStopOrderEntity() {
        return stopOrderEntity;
    }
}
