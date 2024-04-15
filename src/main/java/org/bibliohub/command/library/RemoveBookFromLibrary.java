package org.bibliohub.command.library;

public class RemoveBookFromLibrary extends Library {
    private String password;
    private long id;

    public RemoveBookFromLibrary(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        libraryService.removeBookById(id, password);
    }

}
