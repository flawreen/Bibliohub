package org.bibliohub.command.user;

public class AddUserCommand extends UserCommand {
    private String password;
    public AddUserCommand(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        userService.addUser(password);
    }
}
