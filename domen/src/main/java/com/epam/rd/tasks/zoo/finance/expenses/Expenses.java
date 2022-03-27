package com.epam.rd.tasks.zoo.finance.expenses;

import com.epam.rd.tasks.zoo.finance.Finance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Expenses extends Finance {
    public Expenses(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
