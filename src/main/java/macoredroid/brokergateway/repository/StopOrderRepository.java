package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.DTO.Status;
import macoredroid.brokergateway.DTO.StopOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopOrderRepository extends MongoRepository<StopOrder, String> {
    public StopOrder findStopOrderById(String id);
    public List<StopOrder> findStopOrdersByCreationTimeEqualsAndStatusEquals(String date, Status status);
}
