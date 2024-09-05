package com.schoolmanagementsystem.users;

import java.time.LocalDate;

public class Employee extends People {
    private String designation;
    private int salary;
    private LocalDate joining_date;
    private String pass;

    Employee() {
        super();
    }

    public Employee(int id, String name, String contact, String address, LocalDate date_of_birth, String gender,
            String father, String mother, String religion, String designation, String pass) {
        super(id, name, contact, address, date_of_birth, gender, father, mother, religion);
        this.designation = designation;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJoinigdate(LocalDate joining_date) {
        this.joining_date = joining_date;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDesignation() {
        return this.designation;
    }

    public int getSalary() {
        return this.salary;
    }

    public LocalDate getJoiningdate() {
        return this.joining_date;
    }

}