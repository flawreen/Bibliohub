package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class ReturnBookCommand extends UserCommand {
    private long bookId;

    public ReturnBookCommand(User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.returnBook(user.getId(), bookId);
    }

}
