package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.StopOrderEntity;
import macoredroid.brokergateway.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StopOrderRepository extends MongoRepository<StopOrderEntity, String> {
    public StopOrderEntity findStopOrderById(String id);
    public List<StopOrderEntity> findStopOrdersByCreationTimeEqualsAndStatusEquals(String date, Status status);
}
