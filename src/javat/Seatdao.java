package javat;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Service
public class Seatdao {
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) { this.template = template;}

    public List<Customer> display()throws ClassNotFoundException, SQLException{
        return template.query("select*from customer", (RowMapper)(rs, row)->{
            Customer seat = new Customer();

            seat.setId(rs.getInt(1));
            seat.setName(rs.getString(2));
            seat.setSeatno(rs.getString(3));
            seat.setTdate(rs.getDate(4));

            return seat;
        });
    }
    public Seatdao(JdbcTemplate template){this.template = template;}

    public int insertData(final Customer customer){
        return template.update("insert into customer values (?,?,?,?)", customer.getId(),customer.getName(), customer.getSeatno(), customer.getTdate());
    }

    public int deleteData(int id){return template.update("delete from customer where id = ?", id);}

    public int editData(final Customer customer, int id) {return template.update("update customer set id=?, name=?, seatno = ?, tdate = ? where id = ?", customer.getId(), customer.getName(), customer.getSeatno(), customer.getTdate(), id);}


    public List<Map<String, Object>> getCus(String seatCode){
        return template.queryForList("select * from customer where  id  = ?", seatCode);
    }
}