package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.Domain.Side;
import macoredroid.brokergateway.Domain.Type;
@Data
public class RealCancelOrder {

    Type targetType;

    int unitPrice;
    Side side;

    String targetId;

    private String futureName;

}
