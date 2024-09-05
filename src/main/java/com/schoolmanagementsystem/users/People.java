package com.schoolmanagementsystem.users;

import java.time.LocalDate;

public class People {
    private String name;
    private int id;
    private String contact;
    private String address;
    private LocalDate date_of_birth;
    private String gender;
    private String father;
    private String mother;
    private String religion;

    public People(int id, String name, String contact, String address, LocalDate date_of_birth, String gender,
            String father, String mother, String religion) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.religion = religion;
    }

    public People() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateofbirth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public String getContact() {
        return this.contact;
    }

    public String getAdderss() {
        return this.address;
    }

    public LocalDate getDateofbirth() {
        return this.date_of_birth;
    }

    public String getGender() {
        return this.gender;
    }

    public String getFather() {
        return this.father;
    }

    public String getMother() {
        return this.mother;
    }

    public String getReligion() {
        return religion;
    }

}
