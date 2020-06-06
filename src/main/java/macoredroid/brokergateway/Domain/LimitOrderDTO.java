package macoredroid.brokergateway.Domain;

import lombok.Data;
import macoredroid.brokergateway.util.DTOConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class LimitOrderDTO implements OrderDTO{
    static class Convert implements DTOConvert<LimitOrderDTO, LimitOrder> {

        @Override
        public LimitOrder convertFrom(LimitOrderDTO limitOrderDTO) {
            LimitOrder limitOrder = new LimitOrder();
            BeanUtils.copyProperties(limitOrderDTO, limitOrder);
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
