package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Domain.MarketOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketOrderEvent {
    @TargetAggregateIdentifier
    String marketDepthId;
    MarketOrderDTO marketOrderDTO;
    public IssueMarketOrderEvent(String marketDepthId, MarketOrderDTO marketOrderDTO) {
        this.marketDepthId = marketDepthId;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public MarketOrderDTO getMarketOrderDTO() {
        return marketOrderDTO;
    }

    public void setMarketOrderDTO(MarketOrderDTO marketOrderDTO) {
        this.marketOrderDTO = marketOrderDTO;
    }
}
