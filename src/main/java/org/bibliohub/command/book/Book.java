package org.bibliohub.command.book;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.BookService;

public abstract class Book implements Command {
    protected static final BookService bookService = BookService.getInstance();

    Book() {

    }

    public abstract void execute();
}
