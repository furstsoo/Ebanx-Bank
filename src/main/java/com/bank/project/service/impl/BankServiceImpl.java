package com.bank.project.service.impl;

import com.bank.project.Mapper.Mapper;
import com.bank.project.entity.*;
import com.bank.project.service.BankService;
import com.bank.project.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

@Service
@Slf4j
public class BankServiceImpl implements BankService {
    @Override
    public BigDecimal findBalance(Integer accountId) throws IOException {
        //Arrays.asList(100, 200, 300).contains(accountId)
        return FileUtil.findFile(String.valueOf(accountId)) ? FileUtil.readFile(String.valueOf(accountId)).getBalance() :
                BigDecimal.ZERO;
    }

    @Override
    public Object transaction(TransactionRequest request) throws IOException {
        return switch (request.getType()) {
            case "deposit"-> deposit(request);
            case "withdraw" -> withdraw(request);
            case "transfer" -> transfer(request);
            default -> throw new IllegalStateException("Unexpected value: " + request.getType());
        };
    }

    private Transfer transfer(TransactionRequest request) {
        return null;
    }

    private Origin withdraw(TransactionRequest request) {
        return null;
    }

    private Destination deposit(TransactionRequest request) throws IOException {
        if (FileUtil.findFile(request.getDestination())) {

            //ler arquivo
            Account account = FileUtil.readFile(request.getDestination());

            //mudar o saldo = saldo inicial cadastrado + valor novo = novo saldo
            account.setBalance(request.getAmount().add(account.getBalance()));
            log.info(">>> deposit made successfully <<<");

            // altera o arquivo com o saldo novo
            FileUtil.updateAndCreateFile(request.getDestination(), account);

            //monta o payload para enviar pra controller
            return Mapper.getDestination(account);

        } else {
            //não tem arquivo
            // cria um arquivo novo com base no id da conta
            Account account = Mapper.accountMapper(request);
            FileUtil.updateAndCreateFile(request.getDestination(), account);

            //monta o payload para enviar pra controller
            return Mapper.getDestination(account);
        }
    }
}