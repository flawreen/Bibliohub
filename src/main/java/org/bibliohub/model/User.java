package org.bibliohub.model;

public class User {
    private long id;
    private String name;

    private long companyId;

    private Company company;

    public User() {
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public User(long id, String name, long companyId, Company company) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.company = company;
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
