package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.DTO.CancelOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CancelOrderRepository extends MongoRepository<CancelOrder, String> {
    public CancelOrder findCancelOrderById(String id);
}
