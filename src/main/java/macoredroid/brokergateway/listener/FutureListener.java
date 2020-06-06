package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueFutureEvent;
import macoredroid.brokergateway.Domain.FutureDTO;
import macoredroid.brokergateway.repository.FutureDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FutureListener {
    @Autowired
    FutureDTORepository futureDTORepository;

    @EventHandler
    public void on(IssueFutureEvent issueFutureEvent){
        FutureDTO futureDTO = issueFutureEvent.getFutureDTO();
        futureDTO.setId(issueFutureEvent.getId());
        if(futureDTORepository.findFutureDTOByNameEquals(futureDTO.getName())!=null)
        {
            return;
        }
        futureDTORepository.save(futureDTO);
    }
}
