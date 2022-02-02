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
    @Field(name = "product_type")
    private String productType;
    @Field(name = "account_type")
    private String account_type;
    @Field(name = "account_number")
    private String accountNumber;
    @Field(name = "total_amount")
    private Float totalAmount;
    @Field(name = "total_boot_coin")
    private Float totalBootCoin;
    //client is Bank
    @Field(name = "sub_account_number")
    private String subAccountNumber;
    @Field(name = "max_movements")
    private Integer maxMovements;
    @Field(name = "rest_movements")
    private Integer restMovements;
    @Field(name = "commission_amount")
    private Float commissionAmount;
    @Field(name = "commission_transaction")
    private Float commissionTransaction;
    @Field(name = "total_comission")
    private Float totalCommission;
    @Field(name = "total_credit")
    private Float totalCredit;
    @Field(name = "rest_credit")
    private Float restCredit;
    @Field(name = "date_action")
    private Integer dateAction;
    @Field(name = "min_opening_amount")
    private Float minOpeningAmount;
    //pass
    @Field(name = "is_active")
    private Boolean active;
    @Field(name = "created_at")
    private LocalDate createdAt;
    @Field(name = "expired_date")
    private LocalDate expiredDate;
    @Field(name = "created_by")
    private String createdBy;
}