package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.Domain.FutureDTO;
import macoredroid.brokergateway.RealDTO.RealFutureDTO;
import macoredroid.brokergateway.command.IssueFutureCommand;
import macoredroid.brokergateway.repository.FutureDTORepository;
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
    FutureDTORepository futureDTORepository;
    @PostMapping
    public void addFuture(@RequestBody RealFutureDTO Realfuture){
        if(futureDTORepository.findFutureDTOByNameEquals(Realfuture.getName())!=null)
        {
            return;
        }
        FutureDTO future=new FutureDTO();
        future.setName(Realfuture.getName());
        future.setMarketDepthId(UUID.randomUUID().toString());
        commandGateway.send(new IssueFutureCommand(UUID.randomUUID().toString(), future));
    }
}
