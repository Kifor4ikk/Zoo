package com.epam.rd.tasks.zoo.finance;

import com.epam.rd.tasks.zoo.exception.WrongCountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Finance {
    private String describe;
    private LocalDateTime localDateTime;
    private BigDecimal cost;
    private boolean isDeleted = false;

    public Finance(String describe, LocalDateTime localDateTime, BigDecimal cost) {
        if(cost.compareTo(BigDecimal.ZERO) < 0)
            throw new WrongCountException("Amount  cant be less than ZERO");
        this.describe = describe;
        this.localDateTime = localDateTime;
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
