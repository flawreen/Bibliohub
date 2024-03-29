package org.bibliohub.command;

import org.bibliohub.model.User;

public class BorrowBookCommand extends UserCommand {

    public BorrowBookCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {
        userService.borrowBook(user.getId(), bookId);
    }
}
