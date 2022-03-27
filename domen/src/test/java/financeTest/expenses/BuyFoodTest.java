package financeTest.expenses;

import com.epam.rd.tasks.zoo.exception.DissimilarityOfValuesException;
import com.epam.rd.tasks.zoo.finance.expenses.BuyFood;
import com.epam.rd.tasks.zoo.food.Wheat;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Test
public class BuyFoodTest {

    BuyFood buyFoodTest = new BuyFood("Food for rabbits", LocalDateTime.now(),BigDecimal.valueOf(12),
            Wheat.class, 12, BigDecimal.ONE);

    public void describeTest(){
        Assert.assertEquals(buyFoodTest.getDescribe(),"Food for rabbits");
        buyFoodTest.setDescribe("Food for little rabbits");
        Assert.assertNotEquals(buyFoodTest.getDescribe(),"Food for rabbits");
        Assert.assertEquals(buyFoodTest.getDescribe(),"Food for little rabbits");
    }

    @Test(expectedExceptions = DissimilarityOfValuesException.class)
    public void costTest(){
        Assert.assertEquals(buyFoodTest.getCost(), BigDecimal.valueOf(12));
        BuyFood buyFoodTest2 = new BuyFood("Food for rabbits", LocalDateTime.now(),BigDecimal.valueOf(12),
                Wheat.class, 13, BigDecimal.ONE);
    }

    public void typeOfFoodTest() {
        Assert.assertEquals(buyFoodTest.getTypeOfFood(), Wheat.class);
    }

    public void countTest(){
        Assert.assertEquals(buyFoodTest.getCount(), 12);
    }

    public void pricePerEachTest(){
        Assert.assertEquals(buyFoodTest.getPricePerEach(), BigDecimal.ONE);
    }


}
