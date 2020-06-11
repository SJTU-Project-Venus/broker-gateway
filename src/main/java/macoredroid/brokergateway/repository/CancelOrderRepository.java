package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.CancelOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelOrderRepository extends MongoRepository<CancelOrder, String> {
    public CancelOrder findCancelOrderById(String id);
}
