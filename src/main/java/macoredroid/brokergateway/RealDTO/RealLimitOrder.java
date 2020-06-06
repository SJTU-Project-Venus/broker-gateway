package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.Domain.Side;

@Data
public class RealLimitOrder {

    private String futureName;

    private int totalCount;

    private int unitPrice;

    private Side side;

    private String traderName;

    private String clientId;

}
