package macoredroid.brokergateway.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class FutureEntity {
    @Id
    String id;
    String name;
    String marketDepthId;


}
