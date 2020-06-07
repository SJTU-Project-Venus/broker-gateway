package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.model.Status;
import macoredroid.brokergateway.Entity.StopOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopOrderRepository extends MongoRepository<StopOrderEntity, String> {
    public StopOrderEntity findStopOrderById(String id);
    public List<StopOrderEntity> findStopOrdersByCreationTimeEqualsAndStatusEquals(String date, Status status);
}
