package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
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
@SessionAttributes({"id","errorMessage"})
//id is capturing id passing from index xml for updatetodo to execute function
//errorMessage is used to pass the error message if data has already exist

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
    public String addtodo (ModelMap model, @RequestParam("employeeId") String employeeId, @RequestParam("dob") String dob, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           @RequestParam("gender") String gender, @RequestParam("hireDate") String hireDate, @RequestParam("hourRate") Double hourRate) throws ParseException {

        List<Map<String,Object>> x = dao.getEmp(employeeId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        x.forEach(rowMap -> {
            String iid = (String) rowMap.get("employeeId");
            Date ddob = (Date) rowMap.get("dob");
            String ffirstName = (String) rowMap.get("firstName");
            String llastName = (String) rowMap.get("lastName");
            String ggender = (String) rowMap.get("gender");
            Date hhireDate = (Date) rowMap.get("hireDate");
            Double hhourRate = (Double) rowMap.get("hourRate");

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
    @GetMapping("/update-todo")
    public String showEditPage(ModelMap model, @RequestParam(defaultValue = "")String id)throws SQLException{
        //Parameter must use the same name("id") as in index.html
        model.put("id", id);
        //This one also must use the same name("id") as in index.html
        List<Map<String, Object>> x = dao.getEmp(id);

        x.forEach(rowMap ->{
            String iid = (String) rowMap.get("employeeId");
            Date ddob = (Date) rowMap.get("dob");
            String ffirstName = (String) rowMap.get("firstName");
            String llastName = (String) rowMap.get("lastName");
            String ggender = (String) rowMap.get("gender");
            Date hhireDate = (Date) rowMap.get("hireDate");
            Double hhourRate = (Double) rowMap.get("hourRate");

            model.put("id", iid);
            model.put("dob", ddob);
            model.put("firstname", ffirstName);
            model.put("lastname", llastName);
            model.put("gender", ggender);
            model.put("hireDate", hhireDate);
            model.put("houtRate", hhourRate);
        });
        return "empedit";
    }

    @PostMapping("/update-todo")
    public String editEmpPage(ModelMap model, @RequestParam("employeeId") String employeeId, @RequestParam("dob") String dob, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                              @RequestParam("gender") String gender, @RequestParam("hireDate") String hireDate, @RequestParam("hourRate") Double hourRate) throws ClassNotFoundException, SQLException, ParseException {
        String iid= (String)model.get("id");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dob1 = format.parse(dob);
        Date hiredate2 = format.parse(hireDate);

        Employee emp = new Employee();
        emp.setEmployeeId(employeeId);
        emp.setDob(dob1);
        emp.setFirstname(firstName);
        emp.setLastname(lastName);
        emp.setGender(gender);
        emp.setHireDate(hiredate2);
        emp.setHourRate(hourRate);
        dao.editData(emp, iid);

        return "redirect:/";
    }
    @GetMapping("/delete-todo")
    public String deleteEmpPage(ModelMap model, @RequestParam String id)throws SQLException, ClassNotFoundException{
        dao.deleteData(id);
        model.clear();
        return "redirect:/";
    }
}
