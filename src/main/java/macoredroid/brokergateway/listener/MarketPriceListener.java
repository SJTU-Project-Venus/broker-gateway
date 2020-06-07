package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.NewMarketPriceEvent;
import macoredroid.brokergateway.repository.MarketQuotationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketPriceListener {
    @Autowired
    MarketQuotationRepository marketQuotationRepository;

    @EventHandler
    public void on(NewMarketPriceEvent newMarketPriceEvent){
        marketQuotationRepository.save(newMarketPriceEvent.getMarketPrice());
    }
}
