package macoredroid.brokergateway.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class FutureDTO {
    @Id
    String id;
    String name;
    String marketDepthId;


}
