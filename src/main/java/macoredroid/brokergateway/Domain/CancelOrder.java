package macoredroid.brokergateway.Domain;

import lombok.Data;
import macoredroid.brokergateway.Entity.OrderEntity;
import macoredroid.brokergateway.model.Side;
import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.model.Type;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;
@Data
@Repository
public class CancelOrder implements OrderEntity {
    @Id
    String id;
    Type targetType=Type.LimitOrder;
    int unitPrice=0;
    Side side=Side.BUYER;
    String targetId="";
    private String futureName;
    String marketDepthId="";
    Status status=Status.WAITING;
    String creationTime="";
    String traderName="";
    String statusSwitchTime="";
    private String traderDetailName;
    private String otherId;

    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
}
