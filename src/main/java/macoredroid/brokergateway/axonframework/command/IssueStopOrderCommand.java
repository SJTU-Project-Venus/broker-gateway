package macoredroid.brokergateway.axonframework.command;

import macoredroid.brokergateway.DTO.StopOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueStopOrderCommand implements IssueOrderCommand{
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrder stopOrder;

    public IssueStopOrderCommand(String marketDepthId, StopOrder stopOrder) {
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
