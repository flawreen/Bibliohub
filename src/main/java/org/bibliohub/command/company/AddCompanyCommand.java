package org.bibliohub.command.company;

public class AddCompanyCommand extends CompanyCommand {
    private String password;

    public AddCompanyCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        companyService.addCompany(password);
    }

}
