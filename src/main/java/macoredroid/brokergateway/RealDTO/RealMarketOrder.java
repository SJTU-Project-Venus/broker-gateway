package macoredroid.brokergateway.RealDTO;

import lombok.Data;
import macoredroid.brokergateway.DTO.Side;
import macoredroid.brokergateway.DTO.Status;
@Data
public class RealMarketOrder {

    private Side side;
    private String traderName;
    private Status status;
    private int totalCount;
    private String futureName;
    private String clientId;

}
