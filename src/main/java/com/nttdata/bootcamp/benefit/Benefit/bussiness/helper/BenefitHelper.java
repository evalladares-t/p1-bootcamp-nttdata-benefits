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
import java.util.UUID;

@Component
public class BenefitHelper {

  public Mono<Benefit> createObjectBenefit(CustomerDto customerDto, ProductDto productDto, Benefit benefit, List<Benefit> benefitList) {
    benefit.setCreatedAt(LocalDate.now());
    benefit.setActive(true);
    benefit.setProductType(productDto.getProductType());
    benefit.setProductType(productDto.getProductType());
    if(productDto.getClientBank()){
      benefit = benefitClientBank(benefit, productDto);
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
    }
    else{
      if(benefit.getProductType().equals(constantProduct.WALLET.name()) && isValidWallet(customerDto)){
        benefit.setTotalAmount((float) 0);
        return Mono.just(benefit);
      }
      if(benefit.getProductType().equals(constantProduct.P2P.name()) && isValidP2P(customerDto)){
        benefit.setTotalBootCoin((float) 0);
        return Mono.just(benefit);
      }
    }
    return Mono.empty();
  }

  public Benefit benefitClientBank(Benefit benefit, ProductDto productDto){
    benefit.setAccountNumber(UUID.randomUUID().toString());
    benefit.setDateAction(productDto.getDateAction());
    benefit.setMaxMovements(productDto.getMaxMovements());
    benefit.setCommissionTransaction(productDto.getCommission());
    benefit.setRestMovements(productDto.getMaxMovements());
    return benefit;
  }

  public Boolean isValidWallet(CustomerDto customerDto){
    if(customerDto.getPhone()!=null && customerDto.getDocumentNumber()!=null
            && customerDto.getImeiPhone()!=null && customerDto.getEmail()!=null){
      return true;
    }
    return false;
  }

  public Boolean isValidP2P(CustomerDto customerDto){
    if(customerDto.getPhone()!=null && customerDto.getDocumentNumber()!=null
            && customerDto.getEmail()!=null){
      return true;
    }
    return false;
  }

  public Boolean vendorValidate(List<Benefit> benefitList){

    if(benefitList.stream().filter(benefit -> {
      return (benefit.getProductType().equals(constantProduct.SAVING.name()) && benefit.getActive())||
              (benefit.getProductType().equals(constantProduct.CURRENT.name()) && benefit.getActive()) ||
              (benefit.getProductType().equals(constantProduct.WALLET.name()) && benefit.getActive());
            }
    ).count() >0){
      return true;
    }
    return false;
  }

}
