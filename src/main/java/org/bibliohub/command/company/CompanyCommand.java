package org.bibliohub.command.company;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.CompanyService;

public abstract class CompanyCommand implements Command {
    protected static final CompanyService companyService = CompanyService.getInstance();

    CompanyCommand() {

    }

    public abstract void execute();
}
