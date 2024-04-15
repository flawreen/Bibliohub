package org.bibliohub.command.user;

public class AddUser extends User {
    private String password;
    public AddUser(String password) {
        this.password = password;
    }

    @Override
    public void execute() {
        userService.addUser(password);
    }
}
