package macoredroid.brokergateway.axonframework.listener;

import macoredroid.brokergateway.axonframework.event.IssueMarketQuotationEvent;
import macoredroid.brokergateway.repository.MarketQuotationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketQuotationListener {
    @Autowired
    MarketQuotationRepository marketQuotationRepository;

    @EventHandler
    public void on(IssueMarketQuotationEvent issueMarketQuotationEvent){
        marketQuotationRepository.save(issueMarketQuotationEvent.getMarketQuotation());
    }
}
