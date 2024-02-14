package com.bank.project.service.impl;

import com.bank.project.service.BankService;
import com.bank.project.util.FileUtil;
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