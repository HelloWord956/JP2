package Entity;

import java.time.LocalDateTime;

public class Employee {
    private int id;
    private String name;
    private Department department;
    private LocalDateTime DoB;
    private Gender gender;
    private String city;
    private String Province;
    private String phoneNumber;

    public Employee() {;}

    public Employee(int id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Employee(int id, String name, Department department, LocalDateTime doB, Gender gender, String city, String province, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.department = department;
        DoB = doB;
        this.gender = gender;
        this.city = city;
        Province = province;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public String getDepartmentName(){
        return department.getName();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDateTime getDoB() {
        return DoB;
    }

    public void setDoB(LocalDateTime doB) {
        DoB = doB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", DoB=" + DoB +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", Province='" + Province + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
