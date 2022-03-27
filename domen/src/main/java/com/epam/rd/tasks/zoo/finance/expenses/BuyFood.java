package com.epam.rd.tasks.zoo.finance.expenses;

import com.epam.rd.tasks.zoo.exception.DissimilarityOfValuesException;
import com.epam.rd.tasks.zoo.food.Food;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BuyFood extends Expenses{

    private Class<? extends Food> typeOfFood;
    private Integer count;
    private BigDecimal pricePerEach;

    public BuyFood(String describe, LocalDateTime localDateTime, BigDecimal cost, Class<? extends Food> typeOfFood, Integer count, BigDecimal pricePerEach) {
        super(describe, localDateTime, cost);
        if(!cost.equals(pricePerEach.multiply(new BigDecimal(count))))
            throw new DissimilarityOfValuesException("Wrong values! PricePerEach * count not equals cost .. Check numbers");
        this.typeOfFood = typeOfFood;
        this.count = count;
        this.pricePerEach = pricePerEach;

    }

    public Class<? extends Food> getTypeOfFood() {
        return typeOfFood;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getPricePerEach() {
        return pricePerEach;
    }
}
