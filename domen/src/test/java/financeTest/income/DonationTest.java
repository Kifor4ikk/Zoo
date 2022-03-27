package financeTest.income;

import com.epam.rd.tasks.zoo.finance.income.Donation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Test
public class DonationTest {

    Donation donation = new Donation("Donation", LocalDateTime.now(), BigDecimal.valueOf(12));

    public void describeTest(){
        Assert.assertEquals(donation.getDescribe(),"Donation");
        donation.setDescribe("Donation for zoo");
        Assert.assertNotEquals(donation.getDescribe(),"Donation");
        Assert.assertEquals(donation.getDescribe(),"Donation for zoo");
    }

    public void costTest(){
        Assert.assertEquals(donation.getCost(), BigDecimal.valueOf(12));
    }

}
