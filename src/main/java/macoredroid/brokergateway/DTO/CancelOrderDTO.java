package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Type;
@Data
public class CancelOrderDTO {

    Type targetType;

    int unitPrice;
    Side side;

    String targetId;

    private String futureName;

}
