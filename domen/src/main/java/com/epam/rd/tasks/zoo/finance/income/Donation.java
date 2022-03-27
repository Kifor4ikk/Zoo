package com.epam.rd.tasks.zoo.finance.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Donation extends Income{
    public Donation(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
