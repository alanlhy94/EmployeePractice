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
public class SeatController {
//GitHub Link: https://github.com/alanlhy94/Final_Exam_HoYeungLam_300356233
    @Autowired
    Seatdao dao;

    @GetMapping("/")
    public String showHomePage(ModelMap model) throws ClassNotFoundException, SQLException {
        List<Customer> list = dao.display();
        model.addAttribute("customerList", list);
        model.put("id",list.get(0).getId());
        model.put("seatNo",list.get(0).getSeatno());
        model.put("name",list.get(0).getName());
        model.put("tdate",list.get(0).getTdate());
        return "index";
    }

    @GetMapping("/index")
    public String showHomePage2(ModelMap model) throws ClassNotFoundException, SQLException {
        List<Customer> list = dao.display();
        model.addAttribute("customerList", list);
        model.put("id",list.get(0).getId());
        model.put("seatNo",list.get(0).getSeatno());
        model.put("name",list.get(0).getName());
        model.put("tdate",list.get(0).getTdate());
        return "index";
    }

    @PostMapping("/addtodo")
    public String addtodo (ModelMap model, @RequestParam("seatNo") String seatno, @RequestParam("name") String name, @RequestParam("tDate") Date tDate ) throws ParseException {

        List<Map<String,Object>> x = dao.getCus(seatno);

        x.forEach(rowMap -> {

            Date ttDate = (Date) rowMap.get("tDate");
            String sseatNo = (String) rowMap.get("seatno");
            String nname = (String) rowMap.get("name");


            model.put("name", nname);
            model.put("seatno", sseatNo);
            model.put("tdate", ttDate);

        });

        if(x.isEmpty() == false){
            model.put("errorMessage", "Seat is already reserved");
            return "redirect:/";
        }


        Customer cc = new Customer();
        cc.setName(name);
        cc.setSeatno(seatno);
        cc.setTdate(tDate);
        dao.insertData(cc);
        model.addAttribute("cc", cc);

        return "redirect:/";
    }

    @GetMapping(path = "/delete-todo")
    public String deleteTodo(ModelMap model, @RequestParam String id) throws SQLException, ClassNotFoundException{
        dao.deleteData(id);
        model.clear();
        return"redirect:/";
    }

    @GetMapping(path = "update-todo")
    public String showUpdateTodoPage(ModelMap model, @RequestParam(defaultValue = "")String seatno) throws SQLException,
            ClassNotFoundException{
        model.put("seatno",seatno);
        List<Map<String, Object>> x = dao.getCus(seatno);
        String iid1, ccdesc1;

        x.forEach(rowMap ->{
            Integer iid = (Integer) rowMap.get("id");
            model.put("id",iid);
            String sseatno = (String) rowMap.get("seatno");
            model.put("seatno", sseatno);
            String nname = (String) rowMap.get("name");
            model.put("name", nname);
            String ttdate = (String) rowMap.get("tdate");
            model.put("tdate", ttdate);

        });
        return "edit";
    }

    @PostMapping(path = "/update-todo")
    public String showUpdate(ModelMap model, @RequestParam int id, @RequestParam String name, @RequestParam String seatno, @RequestParam Date tdate) throws SQLException, ClassNotFoundException{
        Integer iid = (Integer) model.get("id");

        Customer cc = new Customer();

        cc.setId(id);
        cc.setSeatno(name);
        cc.setSeatno(seatno);
        cc.setTdate(tdate);

        dao.editData(cc, iid);

        return "redirect:/";
    }

}
