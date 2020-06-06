package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.DTO.MarketDepthDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDepthDTORepository extends MongoRepository<MarketDepthDTO, String> {

}
