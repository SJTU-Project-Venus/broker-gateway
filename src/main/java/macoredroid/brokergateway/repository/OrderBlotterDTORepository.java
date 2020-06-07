package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.OrderBlotterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBlotterDTORepository extends MongoRepository<OrderBlotterEntity, String> {
}
