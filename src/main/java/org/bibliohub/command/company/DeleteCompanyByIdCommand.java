package org.bibliohub.command.company;

public class DeleteCompanyByIdCommand extends CompanyCommand {
    private String password;
    private long id;

    public DeleteCompanyByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        companyService.deleteCompany(id, password);
    }

}
