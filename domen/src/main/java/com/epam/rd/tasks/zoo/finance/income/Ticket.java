package com.epam.rd.tasks.zoo.finance.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket extends Income{

    public Ticket(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
