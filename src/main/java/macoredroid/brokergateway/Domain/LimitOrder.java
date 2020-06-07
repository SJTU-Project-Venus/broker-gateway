package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.EntityConvert;
import lombok.Data;
import macoredroid.brokergateway.Entity.LimitOrderEntity;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
@Data
public class LimitOrder {
    Logger logger = LoggerFactory.getLogger(LimitOrder.class);
    static class Convert implements EntityConvert<LimitOrder, LimitOrderEntity> {
        @Override
        public LimitOrderEntity convertFrom(LimitOrder limitOrder) {
            LimitOrderEntity limitOrderEntity = new LimitOrderEntity();
            BeanUtils.copyProperties(limitOrder, limitOrderEntity);
            return limitOrderEntity;
        }
    }

    public LimitOrderEntity convertToLimitOrderDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private String id;
    private String marketDepthId;
    private int totalCount;
    private int count;
    private int unitPrice;
    private Side side;
    private Status status;
    private String creationTime;
    private String traderName;
    private String futureName;
    private String clientId;

    public void decreaseCount(int delta){
        this.count -= delta;
        if (this.count == 0) this.status = Status.FINISHED;
    }

    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
}
