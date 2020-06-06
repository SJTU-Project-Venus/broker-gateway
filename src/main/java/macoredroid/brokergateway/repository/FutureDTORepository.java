package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.FutureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureDTORepository extends MongoRepository<FutureDTO, String> {
    public FutureDTO findFutureDTOByNameEquals(String description);
    public FutureDTO findFutureDTOByMarketDepthIdEquals(String marketDepthId);
}
