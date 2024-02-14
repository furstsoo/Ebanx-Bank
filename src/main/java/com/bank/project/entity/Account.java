package com.bank.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Account {
    private Integer accountId;
    private BigDecimal accountBalance;
}
