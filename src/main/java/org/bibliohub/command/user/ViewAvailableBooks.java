package org.bibliohub.command.user;

public class ViewAvailableBooks extends User {

    public ViewAvailableBooks() {
    }

    @Override
    public void execute() {
        userService.viewAvailableBooks();
    }

}
