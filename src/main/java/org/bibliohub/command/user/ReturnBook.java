package org.bibliohub.command.user;

import org.bibliohub.service.AuditService;
import org.bibliohub.service.BookService;

import java.sql.SQLException;
import java.util.Collections;

public class ReturnBook extends User {
    private long bookId;
    AuditService auditService;
    BookService bookService = BookService.getInstance();

    public ReturnBook(org.bibliohub.model.User user, long bookId) throws SQLException {
        super(user);
        this.bookId = bookId;
        this.auditService = new AuditService();
    }

    @Override
    public void execute() throws SQLException {
        if(userService.returnBook(user.getId(), bookId))
            auditService.append(
                    Collections.singletonList(new String[]{user.getName(), "a returnat", bookService.getBookById(bookId).getTitle()})
            );
    }

}
