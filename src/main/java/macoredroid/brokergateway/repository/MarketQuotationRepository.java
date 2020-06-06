package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.MarketQuotation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketQuotationRepository extends MongoRepository<MarketQuotation, String> {
}
