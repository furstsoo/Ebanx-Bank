package com.bank.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {

    private String type;
    private String destination;
    private String origin;
    private BigDecimal amount;

}
