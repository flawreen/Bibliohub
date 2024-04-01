package org.bibliohub.command.user;

public class DeleteUserByIdCommand extends UserCommand {
    private String password;
    public DeleteUserByIdCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        userService.deleteUser(password);
    }

}
