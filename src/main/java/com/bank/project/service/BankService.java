package com.bank.project.service;


import com.bank.project.entity.Account;
import com.bank.project.entity.TransactionRequest;
import com.bank.project.entity.Transfer;

import java.io.IOException;
import java.math.BigDecimal;

public interface BankService {

    BigDecimal findBalance(Integer accountId) throws IOException;

    Object transaction(TransactionRequest request) throws IOException;
}
