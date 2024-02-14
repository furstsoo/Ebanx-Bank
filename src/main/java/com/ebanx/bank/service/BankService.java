package com.ebanx.bank.service;

import com.ebanx.bank.entity.Account;
import com.ebanx.bank.entity.TransactionRequest;

import java.io.IOException;
import java.math.BigDecimal;

public interface BankService {

    BigDecimal findBalance(Integer accountId) throws IOException;
}
