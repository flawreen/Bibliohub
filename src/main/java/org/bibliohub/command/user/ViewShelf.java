package org.bibliohub.command.user;

public class ViewShelf extends User {
    public ViewShelf(org.bibliohub.model.User user) {
        super(user);
    }

    @Override
    public void execute() {
        userService.viewShelf(user.getShelfId());
    }

}
