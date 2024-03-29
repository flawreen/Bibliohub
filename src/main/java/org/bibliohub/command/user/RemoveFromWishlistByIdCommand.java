package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class RemoveFromWishlistByIdCommand extends UserCommand {
    public RemoveFromWishlistByIdCommand(User user) {
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
