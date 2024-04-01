package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class AddToWishlistCommand extends UserCommand {
    private long bookId;
    public AddToWishlistCommand(User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.addToWishlist(user.getId(), bookId);
    }
}
