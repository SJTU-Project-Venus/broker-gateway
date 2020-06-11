package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.MarketPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketQuotationRepository extends MongoRepository<MarketPrice, String> {
}
