package org.bibliohub.model;

import java.util.ArrayList;

public class User {
    private long id;
    private String name;

    private static int nextId = 1;

    private long companyId;

    private long shelfId;
    
    private long wishlistId;

    private Company company;

    public User() {
    }

    public static int getNextId() {
        return nextId;
    }

    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public User(long id, String name, long companyId, long shelfId, long wishlistId, Company company) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.shelfId = shelfId;
        this.wishlistId = wishlistId;
        this.company = company;
        nextId++;
    }

    public User(long id, String name, long companyId, long shelfId, Company company) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.shelfId = shelfId;
        this.company = company;
        nextId++;
    }

    public long getShelfId() {
        return shelfId;
    }

    public void setShelfId(long shelfId) {
        this.shelfId = shelfId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return this.getId() + ") Name: " + this.getName() + "; Company: " + this.getCompany().getName();
    }
}
