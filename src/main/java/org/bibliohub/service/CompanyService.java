package org.bibliohub.service;

import org.bibliohub.config.AppDb;
import org.bibliohub.model.Book;
import org.bibliohub.model.Company;
import org.bibliohub.model.Shelf;
import org.bibliohub.model.User;
import org.bibliohub.repository.CompanyRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyService {
    private static CompanyService instance;
    private static final CompanyRepository companyRepository;

    static {
        try {
            companyRepository = CompanyRepository.getInstance("companies");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final UserService userService;

    static {
        try {
            userService = UserService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    public void setCompanies(ArrayList<Company> companies) {
//        this.companies = companies;
//    }

    private Connection db;
    private CompanyService(Connection connection) {
        this.db = connection;
    }

    public static CompanyService getInstance() throws SQLException {
        if (instance == null) {
            instance = new CompanyService(AppDb.getAppDb());
        }
        return instance;
    }

    public Company getCompanyById(long id) throws SQLException {
        return companyRepository.findById((int) id);
//        try {
//            int i = 0;
//            while (companies.get(i).getId() != id) {
//                i++;
//            }
//            return companies.get(i);
//        } catch (IndexOutOfBoundsException e) {
//            return null;
//        }
    }

//    public void addEmployee(long id, User user) {
//        getCompanyById(id).getEmployees().add(user);
//    }

    public void deleteCompany(long id, String password) throws SQLException {
        if (!password.equals("admin")) return;
        companyRepository.deleteById((int) id);
//        try {
//            for (var user : getCompanyById(id).getEmployees()) {
//                userService.deleteUser("admin", user.getId());
//            }
//            companies.remove(getCompanyById(id));
//            System.out.printf("Successfully deleted company with id %d!\n", id);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Company with id " + id + " not found.");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void addCompany(String password) throws SQLException {
        if (!password.equals("admin")) return;
        Scanner read = new Scanner(System.in);
        long id = Company.getNextId();

        System.out.print("\nName: ");
        String name = read.nextLine();

        System.out.print("\nCUI: ");
        long cui = read.nextLong();

        System.out.printf("Successfully added company %s with id %d!\n", name, id);
        companyRepository.insert(name, cui);
    }

    public void addCompany(String name, long cui) throws SQLException {
        companyRepository.insert(name, cui);
    }

    String printCompanies() throws SQLException {
        var companies = companyRepository.getAll();
        var res = new StringBuilder();
        for (var company : companies) {
            res.append(company.getId()).append(". ").append(company.getName()).append("; ");
        }
        return res.toString();
    }

}
