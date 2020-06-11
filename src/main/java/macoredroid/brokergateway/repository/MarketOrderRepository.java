package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.MarketOrderEntity;
import macoredroid.brokergateway.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketOrderRepository extends MongoRepository<MarketOrderEntity, String> {
    public MarketOrderEntity findMarketOrderDTOById(String id);
    public List<MarketOrderEntity> findMarketOrderDTOSByCreationTimeEqualsAndStatusEquals(String date, Status status);
}
