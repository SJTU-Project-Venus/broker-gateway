package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Side;

@Data
public class LimitOrderDTO {

    private String futureName;

    private int totalCount;

    private int unitPrice;

    private Side side;

    private String traderName;

    private String clientId;

}
