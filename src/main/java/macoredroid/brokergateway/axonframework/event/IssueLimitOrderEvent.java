package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.LimitOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueLimitOrderEvent{
    @TargetAggregateIdentifier
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public IssueLimitOrderEvent(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public LimitOrderDTO getLimitOrderDTO() {
        return limitOrderDTO;
    }

    public String getId() {
        return id;
    }
}
