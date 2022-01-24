package com.nttdata.bootcamp.benefit.Benefit.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "benefit")
public class Benefit {

    @Id
    private String id = UUID.randomUUID().toString();
    @Field(name = "customer_id")
    private String customerId;
    @Field(name = "product_id")
    private String productId;
    @Field(name = "rest_movements")
    private Integer restMovements;
    @Field(name = "total_amount")
    private Float totalAmount;
    @Field(name = "total_comission")
    private Float totalCommission;
    @Field(name = "total_credit")
    private Float totalCredit;
    @Field(name = "rest_credit")
    private Float restCredit;
    @Field(name = "created_at")
    private LocalDate createdAt;
    @Field(name = "created_by")
    private String createdBy;
    @Field(name = "is_active")
    private Boolean active;
}