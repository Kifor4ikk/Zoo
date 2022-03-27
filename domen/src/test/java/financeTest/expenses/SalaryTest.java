package financeTest.expenses;

import com.epam.rd.tasks.zoo.finance.expenses.Salary;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Test
public class SalaryTest {
    Salary salary = new Salary("Salary for workers", LocalDateTime.now(), BigDecimal.ONE);

    public void describeTest(){
        Assert.assertEquals(salary.getDescribe(),"Salary for workers");
        salary.setDescribe("Salary for workers in AquaLand");
        Assert.assertNotEquals(salary.getDescribe(),"Salary for workers");
        Assert.assertEquals(salary.getDescribe(),"Salary for workers in AquaLand");
    }

    public void costTest(){
        Assert.assertEquals(salary.getCost(), BigDecimal.valueOf(1));
    }
}
