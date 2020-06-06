package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.DTO.Side;
import macoredroid.brokergateway.DTO.Type;
@Data
public class RealCancelOrder {

    Type targetType;

    int unitPrice;
    Side side;

    String targetId;

    private String futureName;

}
