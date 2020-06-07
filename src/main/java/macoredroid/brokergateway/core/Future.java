package macoredroid.brokergateway.core;

import macoredroid.brokergateway.command.NewFutureCommand;
import macoredroid.brokergateway.event.IssueFutureEvent;
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
        AggregateLifecycle.apply(new IssueFutureEvent(newFutureCommand.getId(), newFutureCommand.getFutureDTO()));
        try {
            AggregateLifecycle.createNew(MarketDepth.class, () -> new MarketDepth(newFutureCommand.getFutureDTO().getMarketDepthId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventSourcingHandler
    public void on(IssueFutureEvent issueFutureEvent){
        BeanUtils.copyProperties(issueFutureEvent.getFutureDTO(), this);
        this.id = issueFutureEvent.getId();
    }

    protected Future(){

    }
}
