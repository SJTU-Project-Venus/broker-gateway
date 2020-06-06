package macoredroid.brokergateway.DTO;

import macoredroid.brokergateway.util.DTOConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
    @ApiModelProperty(required = true)
    private String futureName;
    private String marketDepthId;
    @ApiModelProperty(required = true)
    private int totalCount;
    private int count;
    @ApiModelProperty(required = true)
    private int unitPrice;
    @ApiModelProperty(required = true)
    private Side side;
    private Status status;
    private String creationTime;
    private String traderName;
    @ApiModelProperty(required = true)
    private String clientId;
    private String statusSwitchTime;
}
