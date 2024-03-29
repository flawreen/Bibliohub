package org.bibliohub.model;

import java.util.ArrayList;

public class Company {
    private long id;
    private String name;
    private long cui;

    private ArrayList<User> employees;


    public Company(long id, String name, long cui) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.employees = new ArrayList<User>();
    }

    public Company(long id, String name, ArrayList<User> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public long getCui() {
        return cui;
    }

    public void setCui(long cui) {
        this.cui = cui;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<User> employees) {
        this.employees = employees;
    }

    public Company() {
    }
}
