package macoredroid.brokergateway.Domain;

import lombok.Data;
import macoredroid.brokergateway.util.DTOConvert;
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
    private Side side;
    private String creationTime;
    private String traderName;
    private Status status;
    private int totalCount;
    private String futureName;
    private String clientId;
    private String statusSwitchTime;
}
