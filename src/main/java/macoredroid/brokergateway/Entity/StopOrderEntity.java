package macoredroid.brokergateway.Entity;

import lombok.Data;
import macoredroid.brokergateway.Domain.*;
import macoredroid.brokergateway.DateUtil;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.model.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document
public class StopOrderEntity implements OrderEntity {
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
    private String statusSwitchTime;

    public LimitOrder convertToLimitOrder(){
        LimitOrder limitOrder = new LimitOrder();
        BeanUtils.copyProperties(this, limitOrder);
        limitOrder.setCount(this.getTotalCount());
        limitOrder.setCreationTime(DateUtil.getDate(new Date()));
        limitOrder.setStatus(Status.WAITING);
        return limitOrder;
    }

    public MarketOrder convertToMarketOrder(){
        MarketOrder marketOrder = new MarketOrder();
        BeanUtils.copyProperties(this, marketOrder);
        marketOrder.setCount(this.getTotalCount());
        marketOrder.setCreationTime(DateUtil.getDate(new Date()));
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
