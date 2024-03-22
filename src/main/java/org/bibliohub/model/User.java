package org.bibliohub.model;

import java.util.ArrayList;

public class User {
    private long id;
    private String name;

    private long companyId;

    private long shelfId;
    
    private ArrayList<Wishlist> wishlist;

    private Company company;

    public User() {
    }

    public ArrayList<Wishlist> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<Wishlist> wishlist) {
        this.wishlist = wishlist;
    }

    public User(long id, String name, long companyId, long shelfId, ArrayList<Wishlist> wishlist, Company company) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.shelfId = shelfId;
        this.wishlist = wishlist;
        this.company = company;
    }

    public User(long id, String name, long companyId, long shelfId, Company company) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.shelfId = shelfId;
        this.company = company;
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
}
