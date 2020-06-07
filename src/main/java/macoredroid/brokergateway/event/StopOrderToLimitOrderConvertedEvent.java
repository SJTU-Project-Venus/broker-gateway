package macoredroid.brokergateway.event;
import macoredroid.brokergateway.Entity.LimitOrderEntity;

public class StopOrderToLimitOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final String stopOrderId;
    final LimitOrderEntity limitOrderEntity;

    public StopOrderToLimitOrderConvertedEvent(String id, String stopOrderId, LimitOrderEntity limitOrderEntity) {
        this.id = id;
        this.stopOrderId = stopOrderId;
        this.limitOrderEntity = limitOrderEntity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStopOrderId() {
        return stopOrderId;
    }

    public LimitOrderEntity getLimitOrderEntity() {
        return limitOrderEntity;
    }
}
