package org.bibliohub.command.library;

public class DeleteByIdCommand extends LibraryCommand {
    public DeleteByIdCommand() {
    }

    @Override
    public void execute(String password) {

    }

    @Override
    public void execute(String password, long id) {
        libraryService.removeBookById(id, password);
    }
}
