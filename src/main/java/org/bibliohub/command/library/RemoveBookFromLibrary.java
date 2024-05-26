package org.bibliohub.command.library;

import java.sql.SQLException;

public class RemoveBookFromLibrary extends Library {
    private String password;
    private long id;

    public RemoveBookFromLibrary(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        libraryService.removeBookById(id, password);
    }

}
