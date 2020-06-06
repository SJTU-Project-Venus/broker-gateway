package macoredroid.brokergateway.event;

public class LimitOrderCancelledEvent {
    final String id;
    final String limitOrderId;
    public LimitOrderCancelledEvent(String id, String limitOrderId) {
        this.id = id;
        this.limitOrderId = limitOrderId;
    }

    public String getId() {
        return id;
    }

    public String getLimitOrderId() {
        return limitOrderId;
    }
}
