package macoredroid.brokergateway.axonframework.event;

import macoredroid.brokergateway.DTO.LimitOrderDTO;

public class LimitOrderCountDecreasedEvent {
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public LimitOrderCountDecreasedEvent(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public LimitOrderDTO getLimitOrderDTO(){
        return this.limitOrderDTO;
    }
}
