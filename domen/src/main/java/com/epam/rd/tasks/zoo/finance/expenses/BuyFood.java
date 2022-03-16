package com.epam.rd.tasks.zoo.finance.expenses;

import com.epam.rd.tasks.zoo.food.Food;

import java.math.BigDecimal;

public class BuyFood extends Expenses{
    private Class<? extends Food> typeOfFood;
    private Integer count;
    private BigDecimal pricePerEach;
}
