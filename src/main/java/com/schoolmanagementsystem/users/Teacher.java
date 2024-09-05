package com.schoolmanagementsystem.users;

import java.time.LocalDate;

public class Teacher extends Employee {
    private String subject;
    private int[] clas = new int[10];
    private String[] section = new String[30];

    int iclas = 0;
    int isec = 0;

    Teacher() {

    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setClas(int cl) {
        this.clas[iclas] = cl;
        iclas++;
    }

    public void setSection(String sec) {
        this.section[isec] = sec;
        isec++;
    }

    public Teacher(int id, String name, String contact, String address, LocalDate date_of_birth, String gender,
            String father, String mother, String religion, String designation, String pass, String subject) {
        super(id, name, contact, address, date_of_birth, gender, father, mother, religion, designation, pass);
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public int[] getClas() {
        return this.clas;
    }

    public String[] getSection() {
        return this.section;
    }

}
