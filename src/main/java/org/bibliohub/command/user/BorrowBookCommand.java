package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class BorrowBookCommand extends UserCommand {
    private long bookId;

    public BorrowBookCommand(User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.borrowBook(user.getId(), bookId);
    }
}
