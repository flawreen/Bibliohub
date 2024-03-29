package org.bibliohub.command;

import org.bibliohub.model.User;

public class AddToWishlistCommand extends UserCommand {

    public AddToWishlistCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {
        userService.addToWishlist(user.getId(), bookId);
    }
}
