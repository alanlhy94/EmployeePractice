import javat.Seatdao;
import javat.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SeadaoTest {
    @Mock
    JdbcTemplate mocktemplate;
    @InjectMocks
//    @Autowired
    Seatdao dao;
    int id;
    String name;
    String seatno;

    Date tdate;

    @Test
    public void Reserve(){

        Customer toys = new Customer();
        toys.setId(id);
        toys.setName(name);
        toys.setName(seatno);
        toys.setTdate(tdate);

        lenient().when(mocktemplate.update(anyString(),anyString(),anyString(),anyString())).thenReturn(1);
        dao.insertData(toys);
        ArgumentCaptor<Object> varArgs = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<String> strArgs = ArgumentCaptor.forClass(String.class);
        verify(mocktemplate).update(strArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture(),varArgs.capture());
        assertEquals (id, varArgs.getAllValues().get(0));
        assertEquals (name, varArgs.getAllValues().get(1));
        assertEquals (seatno, varArgs.getAllValues().get(2));
        assertEquals (tdate, varArgs.getAllValues().get(3));

    }

    @Test
    public void delete1(){
        when(mocktemplate.update(anyString(),anyString())).thenReturn(1);

        dao.deleteData(101);

        verify(mocktemplate, times(1)).update(anyString(),anyString());

        ArgumentCaptor<Object> varArgs3 = ArgumentCaptor.forClass(Object.class);

        ArgumentCaptor<String> strArgs3 = ArgumentCaptor.forClass(String.class);

        verify(mocktemplate).update(strArgs3.capture(), varArgs3.capture());

        assertEquals(101, varArgs3.getAllValues().get(0));
    }

    @Test
    public void display()throws SQLException, ClassNotFoundException{

        SimpleDateFormat v = new SimpleDateFormat();
        Customer f = new Customer();
        f.setId(10);
        f.setName("Alan");
        f.setSeatno("3A");

        List<Customer> ll = new ArrayList<Customer>();

        ll.add(f);

        when(mocktemplate.query(anyString(), any(RowMapper.class))).thenReturn(ll);
        List<Customer>ss1 = dao.display();

        assertEquals(1, ss1.size());
    }


}