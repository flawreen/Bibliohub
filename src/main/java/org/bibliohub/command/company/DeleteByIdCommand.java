package org.bibliohub.command.company;

public class DeleteByIdCommand extends CompanyCommand {
    public DeleteByIdCommand() {
    }

    @Override
    public void execute(String password) {

    }

    @Override
    public void execute(String password, long id) {
        companyService.deleteCompany(id, password);
    }
}
