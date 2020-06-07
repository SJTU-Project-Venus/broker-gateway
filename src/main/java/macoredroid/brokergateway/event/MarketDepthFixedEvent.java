package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.MarketDepthEntity;
import macoredroid.brokergateway.Domain.MarketQuotation;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthFixedEvent {
    @TargetAggregateIdentifier
    final String id;
    final MarketDepthEntity marketDepthEntity;
    final MarketQuotation marketQuotation;

    public MarketDepthFixedEvent(String id, MarketDepthEntity marketDepthEntity, MarketQuotation marketQuotation) {
        this.id = id;
        this.marketDepthEntity = marketDepthEntity;
        this.marketQuotation = marketQuotation;
    }

    public String getId() {
        return id;
    }

    public MarketQuotation getMarketQuotation() {
        return marketQuotation;
    }

    public MarketDepthEntity getMarketDepthEntity() {
        return marketDepthEntity;
    }
}
