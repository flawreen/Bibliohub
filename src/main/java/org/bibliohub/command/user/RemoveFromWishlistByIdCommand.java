package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class RemoveFromWishlistByIdCommand extends UserCommand {
    private long bookId;
    public RemoveFromWishlistByIdCommand(User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.removeFromWishlist(user.getId(), bookId);
    }
}
