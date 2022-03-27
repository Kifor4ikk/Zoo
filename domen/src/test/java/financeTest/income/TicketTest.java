package financeTest.income;

import com.epam.rd.tasks.zoo.finance.income.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Test
public class TicketTest {


    Ticket ticket = new Ticket("Ticket", LocalDateTime.now(), BigDecimal.valueOf(12));

    public void describeTest(){
        Assert.assertEquals(ticket.getDescribe(),"Ticket");
        ticket.setDescribe("Ticket for zoo");
        Assert.assertNotEquals(ticket.getDescribe(),"Ticket");
        Assert.assertEquals(ticket.getDescribe(),"Ticket for zoo");
    }

    public void costTest(){
        Assert.assertEquals(ticket.getCost(), BigDecimal.valueOf(12));
    }
}
