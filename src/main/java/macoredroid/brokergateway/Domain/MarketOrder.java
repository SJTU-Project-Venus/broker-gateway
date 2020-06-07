package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.EntityConvert;
import lombok.Data;
import macoredroid.brokergateway.Entity.MarketOrderEntity;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import org.springframework.beans.BeanUtils;
@Data
public class MarketOrder {

    public void decreaseCount(int delta) {
        count -= delta;
        if (count == 0) this.status = Status.FINISHED;
    }


    static class Convert implements EntityConvert<MarketOrder, MarketOrderEntity> {
        @Override
        public MarketOrderEntity convertFrom(MarketOrder marketOrder) {
            MarketOrderEntity marketOrderEntity = new MarketOrderEntity();
            BeanUtils.copyProperties(marketOrder, marketOrderEntity);
            return marketOrderEntity;
        }
    }

    public MarketOrderEntity convertToMarketOrderDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    String id;
    private String futureName;
    private String marketDepthId;
    private int count;
    private Side side;
    private Status status;
    private String creationTime;
    private int totalCount;
    private String traderName;




    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
    }
}
