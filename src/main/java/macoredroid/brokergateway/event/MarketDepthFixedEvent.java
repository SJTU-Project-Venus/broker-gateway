package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.MarketDepthEntity;
import macoredroid.brokergateway.Domain.MarketPrice;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthFixedEvent {
    @TargetAggregateIdentifier
    final String id;
    final MarketDepthEntity marketDepthEntity;
    final MarketPrice marketPrice;

    public MarketDepthFixedEvent(String id, MarketDepthEntity marketDepthEntity, MarketPrice marketPrice) {
        this.id = id;
        this.marketDepthEntity = marketDepthEntity;
        this.marketPrice = marketPrice;
    }

    public String getId() {
        return id;
    }

    public MarketPrice getMarketPrice() {
        return marketPrice;
    }

    public MarketDepthEntity getMarketDepthEntity() {
        return marketDepthEntity;
    }
}
