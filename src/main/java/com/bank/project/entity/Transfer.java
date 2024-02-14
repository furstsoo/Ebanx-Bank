package com.bank.project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transfer {

    private Account origin;
    private Account destination;
}
