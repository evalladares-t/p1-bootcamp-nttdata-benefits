package com.nttdata.bootcamp.benefit.Benefit.bussiness.helper;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.CustomerDto;
import com.nttdata.bootcamp.benefit.Benefit.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientHelper {

  @Autowired
  private WebClient webClientCustomer;

  @Autowired
  private WebClient webClientProduct;

  public Mono<CustomerDto> findCustomer(String id) {
    return webClientCustomer.get()
            .uri(uriBuilder -> uriBuilder.pathSegment(id).build())
            .retrieve()
            .bodyToMono(CustomerDto.class);
  }
  public Mono<ProductDto> findProduct(String id) {
    return webClientProduct.get()
            .uri(uriBuilder -> uriBuilder.pathSegment(id).build())
            .retrieve()
            .bodyToMono(ProductDto.class);
  }
}
