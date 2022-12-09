import javat.Empdao;
import javat.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    JdbcTemplate mockJdbcTemplate;

    @InjectMocks
    Empdao empdao;

    String employeeId;
    Date dob;
    String firstname;
    String lastname;
    String gender;
    Date hireDate;
    float hourRate;

    @Test
    public void addData(){

        Employee a = new Employee();

        lenient().when(mockJdbcTemplate.update(anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn(1);
        empdao.insertData(a);
        ArgumentCaptor<Object> varArgs = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<String> strArgs = ArgumentCaptor.forClass(String.class);

        verify(mockJdbcTemplate).update(strArgs.capture(), varArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture());

        assertEquals(employeeId, varArgs.getAllValues().get(0));
    }


}