package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.DTO.Side;
import macoredroid.brokergateway.DTO.Type;

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
