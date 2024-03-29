package org.bibliohub.command.user;

public class DeleteUserByIdCommand extends UserCommand {
    public DeleteUserByIdCommand() {
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {

    }

    @Override
    public void execute(String password) {
        userService.deleteUser(password);
    }
}
