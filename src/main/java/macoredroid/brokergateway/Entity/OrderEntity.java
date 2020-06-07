package macoredroid.brokergateway.Entity;

import macoredroid.brokergateway.model.Status;

public interface OrderEntity {
    public void setId(String id);
    public void setCreationTime(String time);
    public void setTraderName(String traderName);
    public String getTraderName();
    public String getId();
    public String getMarketDepthId();
    public void setStatus(Status waiting);
    public String getFutureName();
    public void setMarketDepthId(String marketDepthId);
}
