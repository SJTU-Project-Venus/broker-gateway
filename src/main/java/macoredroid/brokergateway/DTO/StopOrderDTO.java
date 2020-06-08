package macoredroid.brokergateway.DTO;

import lombok.Data;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Type;

@Data
public class StopOrderDTO {

    private Type targetType;

    private String futureName;

    private int totalCount;

    private Side side;

    private int unitPrice;

    private int stopPrice;

    private String traderName;

    private String otherId;

}
