package macoredroid.brokergateway.event;

import macoredroid.brokergateway.Domain.MarketPrice;

public class NewMarketPriceEvent {
    private final String id;
    private final MarketPrice marketPrice;

    public NewMarketPriceEvent(String id, MarketPrice marketPrice) {
        this.id = id;
        this.marketPrice = marketPrice;
    }

    public MarketPrice getMarketPrice() {
        return marketPrice;
    }
}
