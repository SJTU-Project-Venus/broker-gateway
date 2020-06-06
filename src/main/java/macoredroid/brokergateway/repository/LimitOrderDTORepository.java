package macoredroid.brokergateway.repository;

import macoredroid.brokergateway.Domain.LimitOrderDTO;
import macoredroid.brokergateway.Domain.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitOrderDTORepository extends MongoRepository<LimitOrderDTO, String> {
    public LimitOrderDTO findLimitOrderDTOById(String id);
    public List<LimitOrderDTO> findLimitOrderDTOSByCreationTimeEqualsAndStatusEquals(String creationTime, Status status);
}
