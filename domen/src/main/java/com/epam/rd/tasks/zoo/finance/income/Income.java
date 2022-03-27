package com.epam.rd.tasks.zoo.finance.income;

import com.epam.rd.tasks.zoo.finance.Finance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Income extends Finance {
    public Income(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
