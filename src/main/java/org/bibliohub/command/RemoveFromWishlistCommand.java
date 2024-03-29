package org.bibliohub.command;

import org.bibliohub.model.User;

public class RemoveFromWishlistCommand extends UserCommand {
    public RemoveFromWishlistCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {
        userService.removeFromWishlist(user.getId(), bookId);
    }

}
