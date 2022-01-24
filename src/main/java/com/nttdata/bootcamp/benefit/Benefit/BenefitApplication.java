package com.nttdata.bootcamp.benefit.Benefit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableReactiveMongoRepositories
public class BenefitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenefitApplication.class, args);
    }

}
