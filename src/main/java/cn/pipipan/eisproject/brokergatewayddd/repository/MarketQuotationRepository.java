package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.DTO.MarketQuotation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketQuotationRepository extends MongoRepository<MarketQuotation, String> {
}
