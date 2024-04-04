package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Company;
import org.bibliohub.model.Shelf;
import org.bibliohub.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CompanyService {
    private static CompanyService instance;
    private ArrayList<Company> companies;

    private CompanyService() {
        this.companies = new ArrayList<>();
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public static CompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }

    public Company getCompanyById(long id) {
        try {
            int i = 0;
            while (companies.get(i).getId() != id) {
                i++;
            }
            return companies.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addEmployee(long id, User user) {
        getCompanyById(id).getEmployees().add(user);
    }

    public void deleteCompany(long id, String password) {
        if (!password.equals("admin")) return;
        try {
            companies.remove(getCompanyById(id));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Company with id " + id + " not found.");
        }
    }

    public void addCompany(String password) {
        if (!password.equals("admin")) return;
        Scanner read = new Scanner(System.in);
        System.out.print("Id: ");
        long id = read.nextLong();
        System.out.print("\nName: ");
        String name = read.next();
        System.out.print("\nCUI: ");
        long cui = read.nextLong();
        companies.add(new Company(id, name, cui));
    }

}
