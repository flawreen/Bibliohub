package org.bibliohub.command.company;

public class AddCommand extends CompanyCommand {
    public AddCommand() {
    }

    @Override
    public void execute(String password) {
        companyService.addCompany(password);
    }

    @Override
    public void execute(String password, long id) {

    }
}
