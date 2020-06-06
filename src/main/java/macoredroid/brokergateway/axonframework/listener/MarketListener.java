package macoredroid.brokergateway.axonframework.listener;

import macoredroid.brokergateway.axonframework.event.MarketClosedEvent;
import macoredroid.brokergateway.DTO.LimitOrderDTO;
import macoredroid.brokergateway.DTO.MarketOrderDTO;
import macoredroid.brokergateway.DTO.Status;
import macoredroid.brokergateway.DTO.StopOrder;
import macoredroid.brokergateway.repository.LimitOrderDTORepository;
import macoredroid.brokergateway.repository.MarketOrderDTORepository;
import macoredroid.brokergateway.repository.StopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketListener {
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;
    @Autowired
    StopOrderRepository stopOrderRepository;

    public void on(MarketClosedEvent marketClosedEvent){
        List<LimitOrderDTO> limitOrderDTOS = limitOrderDTORepository.findLimitOrderDTOSByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        limitOrderDTOS.forEach( l -> l.setStatus(Status.OUTDATED));
        limitOrderDTORepository.saveAll(limitOrderDTOS);

        List<MarketOrderDTO> marketOrderDTOS = marketOrderDTORepository.findMarketOrderDTOSByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        marketOrderDTOS.forEach( l -> l.setStatus(Status.OUTDATED));
        marketOrderDTORepository.saveAll(marketOrderDTOS);

        List<StopOrder> stopOrders = stopOrderRepository.findStopOrdersByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        stopOrders.forEach( l -> l.setStatus(Status.OUTDATED));
        stopOrderRepository.saveAll(stopOrders);
    }
}
