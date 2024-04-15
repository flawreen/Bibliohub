package org.bibliohub.command.company;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.CompanyService;

import java.sql.SQLException;

public abstract class Company implements Command {
    protected static final CompanyService companyService;

    static {
        try {
            companyService = CompanyService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Company() {

    }

    public abstract void execute();
}
