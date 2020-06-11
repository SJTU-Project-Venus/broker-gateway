package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.OrderBlotterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderBlotterRepository extends MongoRepository<OrderBlotterEntity, String> {
}
