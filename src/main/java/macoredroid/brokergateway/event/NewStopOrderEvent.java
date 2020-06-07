package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.StopOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewStopOrderEvent {
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrderEntity stopOrderEntity;

    public NewStopOrderEvent(String marketDepthId, StopOrderEntity stopOrderEntity) {
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
