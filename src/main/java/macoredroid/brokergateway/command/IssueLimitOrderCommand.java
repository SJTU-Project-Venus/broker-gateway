package macoredroid.brokergateway.command;

import macoredroid.brokergateway.Domain.LimitOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueLimitOrderCommand implements IssueOrderCommand{
    @TargetAggregateIdentifier
    final private String id;
    final private LimitOrderDTO limitOrderDTO;

    public IssueLimitOrderCommand(String id, LimitOrderDTO limitOrderDTO) {
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
