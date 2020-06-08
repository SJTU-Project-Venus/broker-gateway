package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Side;
@Data
public class MarketOrderDTO {

    private Side side;
    private String traderName;
    private int totalCount;
    private String futureName;
    private String otherId;

}
