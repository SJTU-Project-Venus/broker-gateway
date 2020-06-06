package macoredroid.brokergateway.DTO;

import macoredroid.brokergateway.util.DTOConvert;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
@Data
public class LimitOrder {
    Logger logger = LoggerFactory.getLogger(LimitOrder.class);
    static class Convert implements DTOConvert<LimitOrder, LimitOrderDTO> {
        @Override
        public LimitOrderDTO convertFrom(LimitOrder limitOrder) {
            LimitOrderDTO limitOrderDTO = new LimitOrderDTO();
            BeanUtils.copyProperties(limitOrder, limitOrderDTO);
            return limitOrderDTO;
        }
    }

    public LimitOrderDTO convertToLimitOrderDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private String id;
    private String marketDepthId;
    private int totalCount;
    private int count;
    private int unitPrice;
    private Side side;
    private Status status;
    private String creationTime;
    private String traderName;
    private String futureName;
    private String clientId;

    public void decreaseCount(int delta){
        this.count -= delta;
        if (this.count == 0) this.status = Status.FINISHED;
    }

    boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
}
