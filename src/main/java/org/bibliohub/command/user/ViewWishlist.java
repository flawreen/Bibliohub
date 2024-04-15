package org.bibliohub.command.user;


public class ViewWishlist extends User {
    public ViewWishlist(org.bibliohub.model.User user) {
        super(user);
    }

    @Override
    public void execute() {
        userService.viewWishlist(user.getWishlistId());
    }

}
