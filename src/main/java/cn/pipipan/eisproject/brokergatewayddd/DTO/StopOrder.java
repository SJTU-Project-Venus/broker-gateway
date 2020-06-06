package cn.pipipan.eisproject.brokergatewayddd.DTO;

import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document
public class StopOrder implements OrderDTO{
    @Id
    private String id;
    @ApiModelProperty(required = true)
    private Type targetType;
    @ApiModelProperty(required = true)
    private String futureName;
    String marketDepthId;
    @ApiModelProperty(required = true)
    private int totalCount;
    @ApiModelProperty(required = true)
    private Side side;
    @ApiModelProperty(notes = "如果是MarketOrder就不需要，如果是LimitOrder就需要", required = true)
    private int unitPrice;
    @ApiModelProperty(required = true, notes = "止损价格")
    private int stopPrice;
    Status status;
    private String creationTime;
    private String traderName;
    @ApiModelProperty(required = true)
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
