package org.bibliohub.command.book;

import org.bibliohub.service.BookService;

public abstract class BookCommand {
    protected static final BookService bookService = BookService.getInstance();

    BookCommand() {

    }

    public abstract void execute(String password);
    public abstract void execute(String password, long id);
}
