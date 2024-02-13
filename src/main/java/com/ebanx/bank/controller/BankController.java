package com.ebanx.bank.controller;


import com.ebanx.bank.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class BankController {

    private BankService bankService;
}
