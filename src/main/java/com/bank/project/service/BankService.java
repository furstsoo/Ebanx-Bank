package com.bank.project.service;


import java.io.IOException;
import java.math.BigDecimal;

public interface BankService {

    BigDecimal findBalance(Integer accountId) throws IOException;
}
