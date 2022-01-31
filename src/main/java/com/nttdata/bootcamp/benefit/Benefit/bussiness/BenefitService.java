package com.nttdata.bootcamp.benefit.Benefit.bussiness;


import com.nttdata.bootcamp.benefit.Benefit.model.Benefit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface BenefitService {
    Mono<Benefit> create(Benefit benefit);
    Flux<Benefit> findAll();
    Mono<Benefit> findById(String benefitId);
    Mono<Benefit> update(Benefit benefit);
    Mono<Benefit> remove(String benefitId);
    Flux<Benefit> findByCustomerId(String customerId);
    Flux<Benefit> findByProductId(String productId);

}
