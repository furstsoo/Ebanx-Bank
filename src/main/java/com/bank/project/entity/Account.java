package com.bank.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {
    private String id;
    private BigDecimal balance;
}
