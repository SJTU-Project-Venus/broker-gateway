package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.MarketDepthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketDepthRepository extends MongoRepository<MarketDepthEntity, String> {

}
