package cn.pipipan.eisproject.brokergatewayddd.DTO;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import lombok.Data;
import org.springframework.beans.BeanUtils;
@Data
public class MarketOrder {

    public void decreaseCount(int delta) {
        count -= delta;
        if (count == 0) this.status = Status.FINISHED;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    static class Convert implements DTOConvert<MarketOrder, MarketOrderDTO> {
        @Override
        public MarketOrderDTO convertFrom(MarketOrder marketOrder) {
            MarketOrderDTO marketOrderDTO = new MarketOrderDTO();
            BeanUtils.copyProperties(marketOrder, marketOrderDTO);
            return marketOrderDTO;
        }
    }

    public MarketOrderDTO convertToMarketOrderDTO(){
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
    private String clientId;




    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
    }
}
