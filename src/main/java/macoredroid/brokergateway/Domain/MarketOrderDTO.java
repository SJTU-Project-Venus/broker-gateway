package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.util.DTOConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class MarketOrderDTO implements OrderDTO{

    static class Convert implements DTOConvert<MarketOrderDTO, MarketOrder> {
        @Override
        public MarketOrder convertFrom(MarketOrderDTO marketOrderDTO) {
            MarketOrder marketOrder = new MarketOrder();
            BeanUtils.copyProperties(marketOrderDTO, marketOrder);
            return marketOrder;
        }
    }

    public MarketOrder convertToMarketOrder(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    @Id
    String id;
    private String marketDepthId;
    private int count;
    @ApiModelProperty(required = true)
    private Side side;
    private String creationTime;
    private String traderName;
    private Status status;
    @ApiModelProperty(required = true)
    private int totalCount;
    @ApiModelProperty(required = true)
    private String futureName;
    @ApiModelProperty(required = true)
    private String clientId;
    private String statusSwitchTime;
}
