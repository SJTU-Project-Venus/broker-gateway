package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.LimitOrderEntity;
import macoredroid.brokergateway.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitOrderRepository extends MongoRepository<LimitOrderEntity, String> {
    public LimitOrderEntity findLimitOrderDTOById(String id);
    public List<LimitOrderEntity> findLimitOrderDTOSByCreationTimeEqualsAndStatusEquals(String creationTime, Status status);
}
