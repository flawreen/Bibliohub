package org.bibliohub.command.library;

public class AddCommand extends LibraryCommand {
    private String password;
    public AddCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        libraryService.addBook(password);
    }

}
