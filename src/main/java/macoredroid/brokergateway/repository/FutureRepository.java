package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Entity.FutureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FutureRepository extends MongoRepository<FutureEntity, String> {
    public FutureEntity findFutureDTOByNameEquals(String description);
    public FutureEntity findFutureDTOByMarketDepthIdEquals(String marketDepthId);
}
