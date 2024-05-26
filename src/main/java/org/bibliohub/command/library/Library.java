package org.bibliohub.command.library;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.LibraryService;

import java.sql.SQLException;

public abstract class Library implements Command {
    protected static final LibraryService libraryService;

    static {
        try {
            libraryService = LibraryService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Library() {

    }

    public abstract void execute() throws SQLException;


}
