package com.nttdata.bootcamp.benefit.Benefit.expose;

import com.nttdata.bootcamp.benefit.Benefit.bussiness.BenefitService;
import com.nttdata.bootcamp.benefit.Benefit.model.Benefit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("api/v1/benefit")
public class BenefitController {

    @Autowired
    private BenefitService benefitService;

    //list
    @GetMapping("")
    public Mono<ResponseEntity<Flux<Benefit>>> findAll() {
        log.info("findAll>>>>>");
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(benefitService.findAll()));
    }

    //Create
    @PostMapping("")
    public Mono<ResponseEntity<Mono<Benefit>>> create(@RequestBody Benefit benefit){
        log.info("Create>>>>>");
      return Mono.just(ResponseEntity.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(benefitService.create(benefit)));
    }

    //Edit
    @PutMapping("")
    public Mono<ResponseEntity<Benefit>> update(@RequestBody Benefit benefit) {
        log.info("update>>>>>");
        return benefitService.update(benefit)
                .flatMap(productUpdate -> Mono.just(ResponseEntity.ok(productUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    //Detail
    @GetMapping("/{id}")
    public Mono<Benefit> show(@PathVariable("id") String id) {
        log.info("byId>>>>>");
        System.out.println(id);
        return benefitService.findById(id);
    }

    //Delete
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Benefit>> delete(@PathVariable("id") String id) {
        log.info("delete>>>>>");
        return benefitService.remove(id)
                .flatMap(benefit -> Mono.just(ResponseEntity.ok(benefit)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
