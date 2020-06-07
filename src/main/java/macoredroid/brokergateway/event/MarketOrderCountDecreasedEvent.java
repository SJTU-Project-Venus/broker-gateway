package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.MarketOrderEntity;

public class MarketOrderCountDecreasedEvent {
    final String id;
    final MarketOrderEntity marketOrderEntity;
    public MarketOrderCountDecreasedEvent(String id, MarketOrderEntity marketOrderEntity) {
        this.id = id;
        this.marketOrderEntity = marketOrderEntity;
    }

    public String getId() {
        return id;
    }

    public MarketOrderEntity getMarketOrderEntity(){
        return this.marketOrderEntity;
    }
}
