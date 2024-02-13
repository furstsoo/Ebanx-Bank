package com.ebanx.bank.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Long accountId;
    private BigDecimal accountBalance;
    private String typeTransaction;
}
