package com.epam.rd.tasks.zoo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {

    private boolean isForChild;
    private BigDecimal price;
    private LocalDateTime soldTime;

    public Ticket(boolean isForChild, BigDecimal price, LocalDateTime soldTime) {
        this.isForChild = isForChild;
        this.price = price;
        this.soldTime = soldTime;
    }

    @Override
    public String toString() {
        return "\nTicket{" +
                "isForChild=" + isForChild +
                ", price=" + price +
                ", soldTime=" + soldTime +
                '}';
    }
}
