package javat;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@Service
public class Empdao {
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) { this.template = template;
    }

    public List<Employee> display()throws ClassNotFoundException, SQLException{
     return template.query("select*from employees", (RowMapper)(rs, row)->{
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getString(1));
        emp.setDob(rs.getDate(2));
        emp.setFirstname(rs.getString(3));
        emp.setLastname(rs.getString(4));
        emp.setGender(rs.getString(5));
        emp.setHireDate(rs.getDate(6));
        emp.setHourRate(rs.getDouble(7));
        return emp;
     });
    }

    public Empdao(JdbcTemplate template){this.template = template;}

    public int insertData(final Employee employee){
        return template.update("insert into employees values (?,?,?,?,?,?,?)", employee.getEmployeeId(), employee.getDob(),employee.getFirstname(),
                employee.getLastname(), employee.getGender(),employee.getHireDate(), employee.getHourRate());
    }

    public int deleteData(Integer empId){return template.update("delete from emplpoyee where employeeId= ?" , empId);   }

    public int editData(final Employee employee, int employeeId)
    {return template.update("update category set employeeId=?, dob=?, firstname = ?, lastname = ?, gender=?, hireDate = ?, hourRate =? where employeeId = ?",
            employee.getEmployeeId(), employee.getDob(), employee.getFirstname(), employee.getLastname(), employee.getGender(), employee.getHireDate(), employee.getHourRate(), employeeId);}

    public List<Map<String, Object>> getEmp(String emp){
        return template.queryForList("select * from employees where  id  = ?", emp);
    }

}
