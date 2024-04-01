package org.bibliohub.command.book;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.BookService;

public abstract class BookCommand implements Command {
    protected static final BookService bookService = BookService.getInstance();

    BookCommand() {

    }

    public abstract void execute();
}
