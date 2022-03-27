package financeTest.income;

import com.epam.rd.tasks.zoo.finance.income.Donation;
import com.epam.rd.tasks.zoo.finance.income.StateSupport;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Test
public class StateSupportTest {

    StateSupport stateSupport = new StateSupport("StateSupport", LocalDateTime.now(), BigDecimal.valueOf(12));

    public void describeTest(){
        Assert.assertEquals(stateSupport.getDescribe(),"StateSupport");
        stateSupport.setDescribe("stateSupport for zoo");
        Assert.assertNotEquals(stateSupport.getDescribe(),"StateSupport");
        Assert.assertEquals(stateSupport.getDescribe(),"stateSupport for zoo");
    }

    public void costTest(){
        Assert.assertEquals(stateSupport.getCost(), BigDecimal.valueOf(12));
    }
}
