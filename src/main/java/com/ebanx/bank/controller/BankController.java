package com.ebanx.bank.controller;


import com.ebanx.bank.service.BankService;
import com.ebanx.bank.util.AccountEnum;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@Slf4j
@AllArgsConstructor
@RestController
public class BankController {

    private BankService bankService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getBalance(@PathParam("account_id") Integer account_id) {

        var accountInfo = AccountEnum.accountById(account_id);
        if (accountInfo != null) {
            return new ResponseEntity<>(accountInfo.getValue(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(BigDecimal.ZERO, HttpStatus.NOT_FOUND);
        }
    }

}
