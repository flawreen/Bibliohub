package org.bibliohub.command.library;

public class AddBookToLibrary extends Library {
    private String password;
    public AddBookToLibrary(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        libraryService.addBook(password);
    }

}
