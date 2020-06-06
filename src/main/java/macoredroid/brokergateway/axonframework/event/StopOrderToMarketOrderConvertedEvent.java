package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.MarketOrderDTO;

public class StopOrderToMarketOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final String stopOrderId;
    final MarketOrderDTO marketOrderDTO;

    public StopOrderToMarketOrderConvertedEvent(String id, String stopOrderId, MarketOrderDTO marketOrderDTO) {
        this.id = id;
        this.stopOrderId = stopOrderId;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStopOrderId() {
        return stopOrderId;
    }

    public MarketOrderDTO getMarketOrderDTO() {
        return marketOrderDTO;
    }
}
