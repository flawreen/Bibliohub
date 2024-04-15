package org.bibliohub.command.user;

public class AddToWishlist extends User {
    private long bookId;
    public AddToWishlist(org.bibliohub.model.User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.addToWishlist(user.getId(), bookId);
    }
}
