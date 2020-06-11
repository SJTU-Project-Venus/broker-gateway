package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.MarketDepthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDepthRepository extends MongoRepository<MarketDepthEntity, String> {

}
