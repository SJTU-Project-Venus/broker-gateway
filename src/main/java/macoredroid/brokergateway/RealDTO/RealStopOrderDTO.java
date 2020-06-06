package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.Domain.Side;
import macoredroid.brokergateway.Domain.Type;

@Data
public class RealStopOrderDTO {

    private Type targetType;

    private String futureName;

    private int totalCount;

    private Side side;

    private int unitPrice;

    private int stopPrice;

    private String traderName;

    private String clientId;

}
