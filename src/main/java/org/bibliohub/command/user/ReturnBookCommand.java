package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class ReturnBookCommand extends UserCommand {

    public ReturnBookCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {
        userService.returnBook(user.getId(), bookId);
    }
}
