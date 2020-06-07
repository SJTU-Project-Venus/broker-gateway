package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
@Data
public class MarketOrderDTO {

    private Side side;
    private String traderName;
    private Status status;
    private int totalCount;
    private String futureName;

}
