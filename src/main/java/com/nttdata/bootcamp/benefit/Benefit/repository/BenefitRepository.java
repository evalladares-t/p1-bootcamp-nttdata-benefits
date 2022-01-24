package com.nttdata.bootcamp.benefit.Benefit.repository;

import com.nttdata.bootcamp.benefit.Benefit.model.Benefit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BenefitRepository extends ReactiveMongoRepository<Benefit, String> {
    Flux<Benefit> findAllByActiveIsTrue();
    Flux<Benefit> findByCustomerIdAndProductId(String customerId, String productId);
}
