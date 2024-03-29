package org.bibliohub.command.company;

import org.bibliohub.service.CompanyService;

public abstract class CompanyCommand {
    protected static final CompanyService companyService = CompanyService.getInstance();

    CompanyCommand() {

    }

    public abstract void execute(String password);
    public abstract void execute(String password, long id);
}
