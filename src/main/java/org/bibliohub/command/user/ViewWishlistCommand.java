package org.bibliohub.command.user;


import org.bibliohub.model.User;

public class ViewWishlistCommand extends UserCommand {
    public ViewWishlistCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {
        userService.viewWishlist(user.getWishlistId());
    }

}
