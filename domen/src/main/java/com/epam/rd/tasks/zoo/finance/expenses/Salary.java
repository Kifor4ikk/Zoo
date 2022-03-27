package com.epam.rd.tasks.zoo.finance.expenses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Salary extends Expenses {
    public Salary(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        super(describe, localDateTime, cost);
    }
}
