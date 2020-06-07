package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.MarketOrderEntity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class NewMarketOrderEvent {
    @TargetAggregateIdentifier
    String marketDepthId;
    MarketOrderEntity marketOrderEntity;
    public NewMarketOrderEvent(String marketDepthId, MarketOrderEntity marketOrderEntity) {
        this.marketDepthId = marketDepthId;
        this.marketOrderEntity = marketOrderEntity;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public MarketOrderEntity getMarketOrderEntity() {
        return marketOrderEntity;
    }

    public void setMarketOrderEntity(MarketOrderEntity marketOrderEntity) {
        this.marketOrderEntity = marketOrderEntity;
    }
}
