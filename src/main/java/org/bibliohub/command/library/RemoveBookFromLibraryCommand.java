package org.bibliohub.command.library;

public class RemoveBookFromLibraryCommand extends LibraryCommand {
    private String password;
    private long id;

    public RemoveBookFromLibraryCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        libraryService.removeBookById(id, password);
    }

}
