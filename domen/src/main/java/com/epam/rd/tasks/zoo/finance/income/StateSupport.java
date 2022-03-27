package com.epam.rd.tasks.zoo.finance.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StateSupport extends Income{

    public StateSupport(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
