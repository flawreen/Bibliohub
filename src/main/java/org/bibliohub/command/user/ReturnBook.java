package org.bibliohub.command.user;

public class ReturnBook extends User {
    private long bookId;

    public ReturnBook(org.bibliohub.model.User user, long bookId) {
        super(user);
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        userService.returnBook(user.getId(), bookId);
    }

}
