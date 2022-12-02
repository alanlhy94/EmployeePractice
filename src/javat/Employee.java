package javat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Employee {
    private int employeeId;
    private Date dob;
    private String firstname;
    private String lastname;
    private String gender;
    private LocalDate hireDate;
    private double hourRate;
    private float totalWorkingHours;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getHourRate() {
        return hourRate;
    }

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    public float getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(float totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }
}
