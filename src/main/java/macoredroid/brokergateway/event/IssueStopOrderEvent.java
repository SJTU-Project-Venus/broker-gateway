package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Domain.StopOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueStopOrderEvent {
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrder stopOrder;

    public IssueStopOrderEvent(String marketDepthId, StopOrder stopOrder) {
        this.marketDepthId = marketDepthId;
        this.stopOrder = stopOrder;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public StopOrder getStopOrder() {
        return stopOrder;
    }
}
