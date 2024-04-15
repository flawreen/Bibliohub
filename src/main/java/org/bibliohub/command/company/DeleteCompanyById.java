package org.bibliohub.command.company;

public class DeleteCompanyById extends Company {
    private String password;
    private long id;

    public DeleteCompanyById(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        companyService.deleteCompany(id, password);
    }

}
