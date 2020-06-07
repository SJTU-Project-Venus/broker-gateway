package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.IssueFutureEvent;
import macoredroid.brokergateway.Entity.FutureEntity;
import macoredroid.brokergateway.repository.FutureRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FutureListener {
    @Autowired
    FutureRepository futureRepository;

    @EventHandler
    public void on(IssueFutureEvent issueFutureEvent){
        FutureEntity futureEntity = issueFutureEvent.getFutureDTO();
        futureEntity.setId(issueFutureEvent.getId());
        //no duplicate
        if(futureRepository.findFutureDTOByNameEquals(futureEntity.getName())!=null)
        {
            return;
        }
        futureRepository.save(futureEntity);
    }
}
