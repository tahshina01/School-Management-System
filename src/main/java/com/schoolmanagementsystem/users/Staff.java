package com.schoolmanagementsystem.users;

import java.time.LocalDate;

public class Staff extends Employee {
    public Staff(int id, String name, String contact, String address, LocalDate date_of_birth, String gender,
            String father, String mother, String religion, String designation, String pass) {
        super(id, name, contact, address, date_of_birth, gender, father, mother, religion, designation, pass);
    }

    Staff() {
        super();
    }
}