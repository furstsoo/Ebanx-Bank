package com.ebanx.bank.entity;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class Account {
    private Integer accountId;
    private BigDecimal accountBalance;
    private String typeTransaction;
}
