package org.bibliohub.service;

import org.bibliohub.model.Book;
import org.bibliohub.model.Company;
import org.bibliohub.model.Shelf;

import java.util.ArrayList;
import java.util.Scanner;

public class CompanyService {
    private static CompanyService instance;
    private ArrayList<Company> companies;

    private CompanyService() {
        this.companies = new ArrayList<>();
    }

    public static CompanyService getInstance() {
        if (instance == null) {
            instance = new CompanyService();
        }
        return instance;
    }

    public Company getCompanyById(long id) {
        try {
            return companies.get((int) id);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void deleteCompany(long id, String password) {
        if (!password.equals("admin")) return;
        try {
            companies.remove(getCompanyById(id));
        } catch (ArrayIndexOutOfBoundsException e) {
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
