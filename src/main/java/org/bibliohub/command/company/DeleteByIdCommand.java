package org.bibliohub.command.company;

public class DeleteByIdCommand extends CompanyCommand {
    private String password;
    private long id;

    public DeleteByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        companyService.deleteCompany(id, password);
    }

}
