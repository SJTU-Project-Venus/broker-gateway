package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.MarketPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketQuotationRepository extends MongoRepository<MarketPrice, String> {
}
