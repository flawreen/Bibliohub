package org.bibliohub.command.library;

public class DeleteByIdCommand extends LibraryCommand {
    private String password;
    private long id;

    public DeleteByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        libraryService.removeBookById(id, password);
    }

}
