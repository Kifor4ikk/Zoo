package com.epam.rd.tasks.zoo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {
    private String cause;
    private BigDecimal cost;
    private LocalDateTime time;

    public Expense(String cause, BigDecimal cost, LocalDateTime time) {
        this.cause = cause;
        this.cost = cost;
        this.time = time;
    }
}
