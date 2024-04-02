package org.bibliohub.command.library;

public class AddBookToLibraryCommand extends LibraryCommand {
    private String password;
    public AddBookToLibraryCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        libraryService.addBook(password);
    }

}
