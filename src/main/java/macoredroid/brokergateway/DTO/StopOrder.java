package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.helper.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document
public class StopOrder implements OrderDTO{
    @Id
    private String id;
    private Type targetType;
    private String futureName;
    String marketDepthId;
    private int totalCount;
    private Side side;
    private int unitPrice;
    private int stopPrice;
    Status status;
    private String creationTime;
    private String traderName;
    private String clientId;
    private String statusSwitchTime;

    public LimitOrder convertToLimitOrder(){
        LimitOrder limitOrder = new LimitOrder();
        BeanUtils.copyProperties(this, limitOrder);
        limitOrder.setCount(this.getTotalCount());
        limitOrder.setCreationTime(Util.getDate(new Date()));
        limitOrder.setStatus(Status.WAITING);
        return limitOrder;
    }

    public MarketOrder convertToMarketOrder(){
        MarketOrder marketOrder = new MarketOrder();
        BeanUtils.copyProperties(this, marketOrder);
        marketOrder.setCount(this.getTotalCount());
        marketOrder.setCreationTime(Util.getDate(new Date()));
        marketOrder.setStatus(Status.WAITING);
        return marketOrder;
    }

    public boolean isBuyer() {
        return this.side.equals(Side.BUYER);
    }

    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
    }

}
