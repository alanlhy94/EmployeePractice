package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
@SessionAttributes({"errorMessage"})
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

    @GetMapping("/index")
    public String showHomePage2(ModelMap model) throws ClassNotFoundException, SQLException {
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

    @PostMapping("/addtodo")
    public String addtodo (ModelMap model, @RequestParam("employeeId") int employeeId, @RequestParam("dob") String dob, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           @RequestParam("gender") String gender, @RequestParam("hireDate") String hireDate, @RequestParam("hourRate") Double hourRate) throws ParseException {

        List<Map<String,Object>> x = dao.getEmp(employeeId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        x.forEach(rowMap -> {
            Integer iid = (Integer) rowMap.get("employeeId");
            Date ddob = (Date) rowMap.get("dob");
            String ffirstName = (String) rowMap.get("firstName");
            String llastName = (String) rowMap.get("lastName");
            String ggender = (String) rowMap.get("gender");
            Date hhireDate = (Date) rowMap.get("hireDate");
            Double hhourRate = (Double) rowMap.get("hourRate");

//                java.sql.Date dob1 = (java.sql.Date)format.parse(ddob);
//                java.sql.Date hiredate2 = (java.sql.Date)format.parse(hhireDate);

                model.put("id", iid);
                model.put("dob", ddob);
                model.put("firstname", ffirstName);
                model.put("lastname", llastName);
                model.put("gender", ggender);
                model.put("hireDate", hhireDate);
                model.put("houtRate", hhourRate);

        });

        if(x.isEmpty() == false){
            model.put("errorMessage", "Record Exisiting");
            return "redirect:/";
        }

        Date dob1 = format.parse(dob);
        Date hiredate2 = format.parse(hireDate);

        Employee cc = new Employee();
        cc.setEmployeeId(employeeId);
        cc.setDob(dob1);
        cc.setFirstname(firstName);
        cc.setLastname(lastName);
        cc.setGender(gender);
        cc.setHireDate(hiredate2);
        cc.setHourRate(hourRate);
        dao.insertData(cc);
        model.addAttribute("cc", cc);


        return "redirect:/";
    }
}
