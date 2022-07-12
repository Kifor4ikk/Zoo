package zoo.repository.animal;

import com.epam.rd.tasks.zoo.repository.animal.AnimalRepositoryImpl;
import com.epam.rd.tasks.zoo.repository.animalhouse.AnimalHouseRepositoryImpl;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Test
public class AnimalRepositoryTest {


    private Connection connection;
    private Statement statement;
    private ResultSet resultSetMock;
    private AnimalRepositoryImpl animalHouseRepository;

    public AnimalRepositoryTest(AnimalRepositoryImpl animalRepository) {
        
    }


}
