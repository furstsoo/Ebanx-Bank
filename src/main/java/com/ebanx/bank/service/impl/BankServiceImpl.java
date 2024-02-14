package com.ebanx.bank.service.impl;

import com.ebanx.bank.entity.Account;
import com.ebanx.bank.service.BankService;
import com.ebanx.bank.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Slf4j
public class BankServiceImpl implements BankService {
    @Override
    public BigDecimal findBalance(Integer accountId) throws IOException {
        return FileUtil.findFile(accountId) ? FileUtil.readFile(accountId).getAccountBalance() :
                BigDecimal.ZERO;
    }
}