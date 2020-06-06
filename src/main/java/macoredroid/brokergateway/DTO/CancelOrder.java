package macoredroid.brokergateway.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;
@Data
@Repository
public class CancelOrder implements OrderDTO{
    @Id
    String id;
    Type targetType;
    @ApiModelProperty(notes = "为了快速找到LimitOrder位置, 如果是LimitOrder就填", required = true)
    int unitPrice;
    @ApiModelProperty(notes = "为了快速找到LimitOrder位置， 如果是LimitOrder就填", required = true)
    Side side;
    @ApiModelProperty(required = true)
    String targetId;
    @ApiModelProperty(required = true)
    private String futureName;
    String marketDepthId;
    Status status;
    String creationTime;
    String traderName;
    String statusSwitchTime;

    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
}
