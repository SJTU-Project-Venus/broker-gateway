package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Entity.MarketOrderEntity;

public class StopOrderToMarketOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final String stopOrderId;
    final MarketOrderEntity marketOrderEntity;

    public StopOrderToMarketOrderConvertedEvent(String id, String stopOrderId, MarketOrderEntity marketOrderEntity) {
        this.id = id;
        this.stopOrderId = stopOrderId;
        this.marketOrderEntity = marketOrderEntity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStopOrderId() {
        return stopOrderId;
    }

    public MarketOrderEntity getMarketOrderEntity() {
        return marketOrderEntity;
    }
}
