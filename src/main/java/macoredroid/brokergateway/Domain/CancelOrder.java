package macoredroid.brokergateway.Domain;

import lombok.Data;
import macoredroid.brokergateway.Entity.OrderEntity;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;
@Data
@Repository
public class CancelOrder implements OrderEntity {
    @Id
    String id;
    Type targetType;
    int unitPrice;
    Side side;
    String targetId;
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
