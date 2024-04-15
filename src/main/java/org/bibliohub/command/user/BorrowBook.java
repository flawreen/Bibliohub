package org.bibliohub.command.user;

public class BorrowBook extends User {
    private long bookId;

    public BorrowBook(org.bibliohub.model.User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.borrowBook(user.getId(), bookId);
    }
}
