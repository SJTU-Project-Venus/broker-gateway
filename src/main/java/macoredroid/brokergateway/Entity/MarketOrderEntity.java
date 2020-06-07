package macoredroid.brokergateway.Entity;

import lombok.Data;
import macoredroid.brokergateway.EntityConvert;
import macoredroid.brokergateway.Domain.MarketOrder;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class MarketOrderEntity implements OrderEntity {

    static class Convert implements EntityConvert<MarketOrderEntity, MarketOrder> {
        @Override
        public MarketOrder convertFrom(MarketOrderEntity marketOrderEntity) {
            MarketOrder marketOrder = new MarketOrder();
            BeanUtils.copyProperties(marketOrderEntity, marketOrder);
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
