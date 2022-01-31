package com.nttdata.bootcamp.benefit.Benefit.bussiness.helper;

import com.nttdata.bootcamp.benefit.Benefit.model.Benefit;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.CustomerDto;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.ProductDto;
import com.nttdata.bootcamp.benefit.Benefit.utils.constantCustomer;
import com.nttdata.bootcamp.benefit.Benefit.utils.constantProduct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Component
public class BenefitHelper {

  public Mono<Benefit> createObjectBenefit(CustomerDto customerDto, ProductDto productDto, Benefit benefit, List<Benefit> benefitList) {
    benefit.setCreatedAt(LocalDate.now());
    benefit.setActive(true);
    benefit.setDateAction(productDto.getDateAction());
    benefit.setMaxMovements(productDto.getMaxMovements());
    benefit.setCommissionTransaction(productDto.getCommission());
    benefit.setProductType(productDto.getProductType());
    benefit.setRestMovements(productDto.getMaxMovements());
    Integer maxCount = productDto.getMaxCountPersonal();
    if(customerDto.getCustomerType().equals(constantCustomer.BUSINESS.name())){
      maxCount = productDto.getMaxCountBusiness();
    }

    if(maxCount==null || maxCount>benefitList.size()){
      if(productDto.getProductType().equals(constantProduct.PASSIVES.name())){
        benefit.setTotalAmount((float) 0);
        if(benefit.getMinOpeningAmount()!=null){
          benefit.setTotalAmount(benefit.getMinOpeningAmount());
        }
        benefit.setTotalCommission((float) 0);
      }
      if(productDto.getProductType().equals(constantProduct.ASSETS.name())){
        benefit.setRestCredit(benefit.getTotalCredit());
      }
      return Mono.just(benefit);
    }
    return Mono.empty();
  }

}
