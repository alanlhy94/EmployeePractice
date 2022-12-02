package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping
public class EmployeeController {

    @Autowired
    javat.Empdao dao;

    @GetMapping("/")
    public String showHomePage(ModelMap model) throws ClassNotFoundException, SQLException {
        List<Employee> list = dao.display();
        model.addAttribute("employeelist", list);
        model.put("id",list.get(0).getEmployeeId());
        model.put("dob",list.get(0).getDob());
        model.put("firstname",list.get(0).getFirstname());
        model.put("lastname",list.get(0).getLastname());
        model.put("hiredate",list.get(0).getHireDate());
        model.put("hourlyrate",list.get(0).getHourRate());
        return "index";
    }

    @GetMapping("/addtodo")
    public String showAddEmpPage(){
        return "caster";
    }

    @PostMapping(path = "/addtodo")
    public String addtodo (ModelMap model, @RequestParam String employeeId){

        return "caster";
    }
}
