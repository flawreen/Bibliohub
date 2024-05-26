package org.bibliohub.command.book;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.BookService;

import java.sql.SQLException;

public abstract class Book implements Command {
    protected static final BookService bookService;

    static {
        try {
            bookService = BookService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Book() {

    }

    public abstract void execute() throws SQLException;
}
