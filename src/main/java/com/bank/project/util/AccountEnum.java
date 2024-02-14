package com.bank.project.util;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum AccountEnum {
    ACCOUNT_ONE(100, BigDecimal.TEN),
    ACCOUNT_TWO(200, BigDecimal.TEN),
    ACCOUNT_THREE(300, BigDecimal.TEN);

    private final Integer id;
    private final BigDecimal value;

    AccountEnum(Integer id, BigDecimal value) {
        this.id = id;
        this.value = value;
    }

    public static AccountEnum accountById(Integer accountId) {
        for (AccountEnum accountEnum : AccountEnum.values()) {
            if (accountId.equals(accountEnum.getId())) {
                return accountEnum;
            }
        }
        return null;
    }
}
