package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Type;

@Data
public class CancelOrderDTO {

    String targetId;
    Type targetType;
    private String futureName;


}
