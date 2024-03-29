package org.bibliohub.command.library;

public class AddCommand extends LibraryCommand {
    public AddCommand() {
    }

    @Override
    public void execute(String password) {
        libraryService.addBook(password);
    }

    @Override
    public void execute(String password, long id) {

    }
}
