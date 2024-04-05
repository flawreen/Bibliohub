package org.bibliohub.command.user;

public class ViewAvailableBooksCommand extends UserCommand {

    public ViewAvailableBooksCommand() {
    }

    @Override
    public void execute() {
        userService.viewAvailableBooks();
    }

}