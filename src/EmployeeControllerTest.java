import javat.Empdao;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {

    @Mock
    JdbcTemplate mockJdbcTemplate;

    @InjectMocks
    Empdao empdao;

    String employeeId;
    String dob;
    String firstname;
    String lastname;

}