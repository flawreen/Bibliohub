package org.bibliohub.command.user;

public class AddUserCommand extends UserCommand {
    public AddUserCommand() {
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {

    }

    @Override
    public void execute(String password) {
        userService.addUser(password);
    }
}
