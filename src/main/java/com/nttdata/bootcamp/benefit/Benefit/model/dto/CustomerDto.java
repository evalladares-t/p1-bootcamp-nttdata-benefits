package com.nttdata.bootcamp.benefit.Benefit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CustomerDto {

  private String customerType;
  private Boolean owner;
  private String documentNumber;
  private String email;
  private String phone;
  private String imeiPhone;
  private Boolean active;

}
