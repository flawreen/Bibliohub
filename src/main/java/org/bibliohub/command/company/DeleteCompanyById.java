package org.bibliohub.command.company;

import java.sql.SQLException;

public class DeleteCompanyById extends Company {
    private String password;
    private long id;

    public DeleteCompanyById(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        companyService.deleteCompany(id, password);
    }

}
