package org.bibliohub.command.user;

public class RemoveFromWishlistById extends User {
    private long bookId;
    public RemoveFromWishlistById(org.bibliohub.model.User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.removeFromWishlist(user.getId(), bookId);
    }
}
