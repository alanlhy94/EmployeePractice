package javat;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private int employeeId;
    private Date dob;
    private String firstname;
    private String lastname;
    private String gender;
    private Date hireDate;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
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
