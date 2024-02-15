package com.bank.project.service.impl;

import com.bank.project.Mapper.Mapper;
import com.bank.project.entity.*;
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
        return FileUtil.findFile(String.valueOf(accountId)) ? FileUtil.readFile(String.valueOf(accountId)).getBalance() :
                BigDecimal.ZERO;
    }

    @Override
    public Object transaction(TransactionRequest request) throws IOException {
        return switch (request.getType()) {
            case "deposit" -> deposit(request);
            case "withdraw" -> withdraw(request);
            case "transfer" -> transfer(request);
            default -> throw new IllegalStateException("Unexpected value: " + request.getType());
        };
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
            // cria um arquivo novo com base no id da conta
            Account account = Mapper.accountMapper(request);
            FileUtil.updateAndCreateFile(request.getDestination(), account);

            //monta o payload para enviar pra controller
            return Mapper.getDestination(FileUtil.readFile(request.getDestination()));
        }
    }

    private Origin withdraw(TransactionRequest request) throws IOException {
        if (FileUtil.findFile(request.getOrigin())) {
            //ler arquivo
            Account account = FileUtil.readFile(request.getOrigin());

            //mudar o saldo = saldo inicial cadastrado + valor novo = novo saldo
            account.setBalance(account.getBalance().subtract(request.getAmount()));
            log.info(">>> withdraw made successfully <<<");

            // altera o arquivo com o saldo novo
            FileUtil.updateAndCreateFile(request.getOrigin(), account);

            //monta o payload para enviar pra controller
            return Mapper.getOrigin(account);

        } else {
            log.error(">>> Account Not Found <<<");
            throw new IllegalStateException();
        }
    }

    private Transfer transfer(TransactionRequest request) throws IOException {
        if (FileUtil.findFile(request.getDestination()) && FileUtil.findFile(request.getOrigin())) {

            //ler arquivo conta origem e conta destino
            Account accountOrigin = FileUtil.readFile(request.getOrigin());
            Account accountDestination = FileUtil.readFile(request.getDestination());


            //mudar o saldo = saldo inicial cadastrado + valor novo = novo saldo
            accountDestination.setBalance(accountDestination.getBalance().add(accountOrigin.getBalance()));
            accountOrigin.setBalance(accountOrigin.getBalance().subtract(request.getAmount()));
            log.info(">>> Transfer made successfully <<<");

            // altera o arquivo com o saldo novo
            FileUtil.updateAndCreateFile(request.getOrigin(), accountOrigin);
            FileUtil.updateAndCreateFile(request.getDestination(), accountDestination);

            //monta o payload para enviar pra controller
            return Mapper.getTransfer(accountOrigin, accountDestination);
        } else {
            log.error(">>> Account Not Found <<<");
            throw new IllegalStateException();
        }
    }

    @Override
    public void reset() throws IOException {
        var file300 = FileUtil.readFile("300");

        file300.setBalance(BigDecimal.ZERO);

        FileUtil.updateAndCreateFile("300", file300);

        log.info(">>> Reset made successfully <<<");

    }

}