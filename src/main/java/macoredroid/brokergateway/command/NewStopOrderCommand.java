package macoredroid.brokergateway.command;

import macoredroid.brokergateway.Entity.StopOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewStopOrderCommand implements NewOrderCommand {
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrderEntity stopOrderEntity;

    public NewStopOrderCommand(String marketDepthId, StopOrderEntity stopOrderEntity) {
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
