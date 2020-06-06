package macoredroid.brokergateway.axonframework.listener;

import macoredroid.brokergateway.axonframework.event.IssueMarketOrderEvent;
import macoredroid.brokergateway.axonframework.event.MarketOrderCancelledEvent;
import macoredroid.brokergateway.axonframework.event.MarketOrderCountDecreasedEvent;
import macoredroid.brokergateway.axonframework.event.StopOrderToMarketOrderConvertedEvent;
import macoredroid.brokergateway.DTO.MarketOrderDTO;
import macoredroid.brokergateway.DTO.Status;
import macoredroid.brokergateway.helper.Util;
import macoredroid.brokergateway.repository.MarketOrderDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MarketOrderListener {
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;

    @EventHandler
    public void on(IssueMarketOrderEvent issueMarketOrderEvent){
        MarketOrderDTO marketOrderDTO = issueMarketOrderEvent.getMarketOrderDTO();
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(MarketOrderCountDecreasedEvent marketOrderCountDecreasedEvent){
        MarketOrderDTO marketOrderDTO = marketOrderCountDecreasedEvent.getMarketOrderDTO();
        marketOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(MarketOrderCancelledEvent marketOrderCancelledEvent){
        MarketOrderDTO marketOrderDTO = marketOrderDTORepository.findMarketOrderDTOById(marketOrderCancelledEvent.getMarketOrderId());
        marketOrderDTO.setStatus(Status.CANCELLED);
        marketOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(StopOrderToMarketOrderConvertedEvent stopOrderToMarketOrderConvertedEvent){
        MarketOrderDTO marketOrderDTO = stopOrderToMarketOrderConvertedEvent.getMarketOrderDTO();
        marketOrderDTORepository.save(marketOrderDTO);
    }
}
