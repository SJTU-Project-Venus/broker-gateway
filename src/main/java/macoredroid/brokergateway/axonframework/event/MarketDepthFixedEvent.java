package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.MarketDepthDTO;
import macoredroid.brokergateway.DTO.MarketQuotation;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthFixedEvent {
    @TargetAggregateIdentifier
    final String id;
    final MarketDepthDTO marketDepthDTO;
    final MarketQuotation marketQuotation;

    public MarketDepthFixedEvent(String id, MarketDepthDTO marketDepthDTO, MarketQuotation marketQuotation) {
        this.id = id;
        this.marketDepthDTO = marketDepthDTO;
        this.marketQuotation = marketQuotation;
    }

    public String getId() {
        return id;
    }

    public MarketQuotation getMarketQuotation() {
        return marketQuotation;
    }

    public MarketDepthDTO getMarketDepthDTO() {
        return marketDepthDTO;
    }
}
