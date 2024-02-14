package com.bank.project.Mapper;

import com.bank.project.entity.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@UtilityClass
public class Mapper {
    public static Account accountMapper(TransactionRequest request) {
        Account account = new Account();
        account.setId(request.getDestination());
        account.setBalance(request.getAmount());

        return account;
    }


    public static Transfer getTransfer(Account transaction) {
        Transfer transfer = new Transfer();

        transfer.setOrigin(getAccount(transaction));
        transfer.setDestination(getAccount(transaction));

        return transfer;
    }

    private static Account getAccount(Account transaction) {
        Account account = new Account();

        account.setId(transaction.getId());
        account.setBalance(transaction.getBalance());

        return account;
    }


    public static Destination getDestination(Account account) {
        Destination destination = new Destination();
        destination.setDestination(getAccount(account));

        return destination;
    }

    public static Origin getOrigin(Account account) {
        Origin origin = new Origin();
        origin.setOrigin(getAccount(account));

        return origin;
    }

}
