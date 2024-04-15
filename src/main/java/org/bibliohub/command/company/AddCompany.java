package org.bibliohub.command.company;

public class AddCompany extends Company {
    private String password;

    public AddCompany(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        companyService.addCompany(password);
    }

}
