package org.bibliohub.command;


import org.bibliohub.model.User;

public class ViewWishlistCommand extends UserCommand {
    public ViewWishlistCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {
        userService.viewWishlist(user.getWishlistId());
    }

    @Override
    public void execute(long bookId) {

    }
}
