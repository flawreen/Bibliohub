package org.bibliohub.command.company;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.CompanyService;

public abstract class Company implements Command {
    protected static final CompanyService companyService = CompanyService.getInstance();

    Company() {

    }

    public abstract void execute();
}
