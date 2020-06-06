package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.MarketOrderDTO;

public class MarketOrderCountDecreasedEvent {
    final String id;
    final MarketOrderDTO marketOrderDTO;
    public MarketOrderCountDecreasedEvent(String id, MarketOrderDTO marketOrderDTO) {
        this.id = id;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getId() {
        return id;
    }

    public MarketOrderDTO getMarketOrderDTO(){
        return this.marketOrderDTO;
    }
}
