package macoredroid.brokergateway.controller;

import macoredroid.brokergateway.DTO.FutureDTO;
import macoredroid.brokergateway.Entity.FutureEntity;
import macoredroid.brokergateway.Entity.MarketDepthEntity;
import macoredroid.brokergateway.command.NewFutureCommand;
import macoredroid.brokergateway.model.Response;
import macoredroid.brokergateway.repository.FutureRepository;
import macoredroid.brokergateway.repository.MarketDepthRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/futures")
public class FutureController {
    @Autowired
    CommandGateway commandGateway;
    @Autowired
    FutureRepository futureRepository;
    @Autowired
    MarketDepthRepository marketDepthRepository;
    @PostMapping("/addFuture")
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

    @GetMapping("/getMarketDepth/{futureName}")
    public Response<MarketDepthEntity> getMarketDepth(@PathVariable String futureName){
        if(futureRepository.findFutureDTOByNameEquals(futureName)==null)
        {
            return new Response<>(null, 500, "no such future");
        }
        String id=futureRepository.findFutureDTOByNameEquals(futureName).getMarketDepthId();
        return new Response<MarketDepthEntity>(marketDepthRepository.findById(id).get(), 200, "ok");
    }
}
