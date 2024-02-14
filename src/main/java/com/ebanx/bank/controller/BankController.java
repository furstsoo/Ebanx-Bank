package com.ebanx.bank.controller;


import com.ebanx.bank.service.BankService;
import com.ebanx.bank.util.AccountEnum;
import com.ebanx.bank.util.FileUtil;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;


@Slf4j
@AllArgsConstructor
@RestController
public class BankController {

    private BankService bankService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getBalance(@PathParam("account_id") Integer account_id) throws IOException {
        log.info(">>> Init find balance <<<");
        var response = bankService.findBalance(account_id);

        return response.equals(BigDecimal.ZERO) ? new ResponseEntity<>(BigDecimal.ZERO, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(response, HttpStatus.OK);
    }

}
