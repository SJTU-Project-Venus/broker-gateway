package macoredroid.brokergateway.core;

import macoredroid.brokergateway.command.NewFutureCommand;
import macoredroid.brokergateway.event.NewFutureEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
@Data
@Aggregate
public class Future {
    @AggregateIdentifier
    String id;
    String marketDepthId;
    String name;

    @CommandHandler
    public Future(NewFutureCommand newFutureCommand){
        AggregateLifecycle.apply(new NewFutureEvent(newFutureCommand.getId(), newFutureCommand.getFutureDTO()));
        try {
            //new marketDepth , very important!
            AggregateLifecycle.createNew(MatchingEngine.class, () -> new MatchingEngine(newFutureCommand.getFutureDTO().getMarketDepthId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventSourcingHandler
    public void on(NewFutureEvent newFutureEvent){
        //copy attributes
        BeanUtils.copyProperties(newFutureEvent.getFutureDTO(), this);
        this.id = newFutureEvent.getId();
    }

    protected Future(){

    }
}
