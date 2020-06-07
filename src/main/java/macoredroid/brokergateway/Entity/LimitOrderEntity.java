package macoredroid.brokergateway.Entity;

import lombok.Data;
import macoredroid.brokergateway.EntityConvert;
import macoredroid.brokergateway.Domain.LimitOrder;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class LimitOrderEntity implements OrderEntity {
    static class Convert implements EntityConvert<LimitOrderEntity, LimitOrder> {

        @Override
        public LimitOrder convertFrom(LimitOrderEntity limitOrderEntity) {
            LimitOrder limitOrder = new LimitOrder();
            BeanUtils.copyProperties(limitOrderEntity, limitOrder);
            return limitOrder;
        }
    }

    public LimitOrder convertToLimitOrder(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }
    @Id
    private String id;
    private String futureName;
    private String marketDepthId;
    private int totalCount;
    private int count;
    private int unitPrice;
    private Side side;
    private Status status;
    private String creationTime;
    private String traderName;
    private String clientId;
    private String statusSwitchTime;
}
