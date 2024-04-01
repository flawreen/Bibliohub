package org.bibliohub.command.company;

public class AddCommand extends CompanyCommand {
    private String password;

    public AddCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        companyService.addCompany(password);
    }

}
