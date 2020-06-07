package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.Entity.FutureEntity;
import macoredroid.brokergateway.DTO.FutureDTO;
import macoredroid.brokergateway.command.NewFutureCommand;
import macoredroid.brokergateway.repository.FutureRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/futures")
public class FutureController {
    @Autowired
    CommandGateway commandGateway;
    @Autowired
    FutureRepository futureRepository;
    @PostMapping
    public void addFuture(@RequestBody FutureDTO futureDTO){
        //no duplicate
        if(futureRepository.findFutureDTOByNameEquals(futureDTO.getName())!=null)
        {
            return;
        }
        FutureEntity future=new FutureEntity();
        future.setName(futureDTO.getName());
        future.setMarketDepthId(UUID.randomUUID().toString());
        commandGateway.send(new NewFutureCommand(UUID.randomUUID().toString(), future));
    }
}
