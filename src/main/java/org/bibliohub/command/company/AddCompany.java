package org.bibliohub.command.company;

import java.sql.SQLException;

public class AddCompany extends Company {
    private String password;

    public AddCompany(String password) {
        this.password = password;
    }

    @Override
    public void execute() throws SQLException {
        companyService.addCompany(password);
    }

}
