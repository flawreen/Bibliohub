package org.bibliohub.command.user;

import org.bibliohub.service.AuditService;
import org.bibliohub.service.BookService;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class BorrowBook extends User {
    private long bookId;
    AuditService auditService;
    BookService bookService = BookService.getInstance();

    public BorrowBook(org.bibliohub.model.User user, long bookId) throws SQLException {
        super(user);
        this.bookId = bookId;
        this.auditService = new AuditService();
    }

    @Override
    public void execute() throws SQLException {
        if (userService.borrowBook(user.getId(), bookId))
            auditService.append(
                    Collections.singletonList(new String[]{user.getName(), "a imprumutat", bookService.getBookById(bookId).getTitle()})
            );
    }
}
