package africa.tanda.tandaPay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import africa.tanda.tandaPay.model.PaymentRequestDTO;

public interface PaymentRequestRepository extends MongoRepository<PaymentRequestDTO, String> {

}
