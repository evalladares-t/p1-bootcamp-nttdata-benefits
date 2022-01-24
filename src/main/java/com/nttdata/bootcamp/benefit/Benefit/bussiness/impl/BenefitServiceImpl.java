package com.nttdata.bootcamp.benefit.Benefit.bussiness.impl;

import com.nttdata.bootcamp.benefit.Benefit.bussiness.helper.BenefitHelper;
import com.nttdata.bootcamp.benefit.Benefit.bussiness.helper.WebClientHelper;
import com.nttdata.bootcamp.benefit.Benefit.model.Benefit;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.CustomerDto;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.ProductDto;
import com.nttdata.bootcamp.benefit.Benefit.repository.BenefitRepository;
import com.nttdata.bootcamp.benefit.Benefit.utils.constantCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.List;

@Service
public class BenefitServiceImpl implements com.nttdata.bootcamp.benefit.Benefit.bussiness.BenefitService {

    @Autowired
    private BenefitRepository benefitRepository;

    @Autowired
    private WebClientHelper webClientHelper;

    @Autowired
    private BenefitHelper benefitHelper;

    @Override
    public Mono<Benefit> create(Benefit benefit) {


      Mono<CustomerDto> customer = webClientHelper.findCustomer(benefit.getCustomerId());

      Mono<ProductDto> product = webClientHelper.findProduct(benefit.getProductId());

      Flux<Benefit>  benefitFlux = benefitRepository.findByCustomerIdAndProductId(benefit.getCustomerId(), benefit.getProductId());

      Mono<List<Benefit>> benefitList = benefitFlux.collectList();

      Mono<Benefit> benefitMono =  customer.flatMap(customerDto -> product.flatMap(productDto -> {
        return benefitList.flatMap(bl -> benefitHelper.createObjectBenefit(customerDto, productDto, benefit, bl));
      }));
      return benefitMono.filter(b->b!=null).flatMap(b->benefitRepository.save(b));
    }

    @Override
    public Flux<Benefit> findAll() {
        return benefitRepository.findAllByActiveIsTrue();
    }

    @Override
    public Mono<Benefit> findById(String benefitId) {
        return benefitRepository.findById(benefitId);
    }

    @Override
    public Mono<Benefit> update(Benefit benefit) {
        return benefitRepository.findById(benefit.getId())
                .flatMap(productDB -> {
                    return benefitRepository.save(benefit);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Benefit> remove(String benefitId) {
        return benefitRepository
                .findById(benefitId)
                .flatMap(p -> {
                    p.setActive(false);
                    return benefitRepository.save(p);
                });
    }

}