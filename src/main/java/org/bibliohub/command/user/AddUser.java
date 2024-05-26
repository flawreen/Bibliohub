package org.bibliohub.command.user;

import java.sql.SQLException;

public class AddUser extends User {
    private String password;
    public AddUser(String password) {
        this.password = password;
    }

    @Override
    public void execute() throws SQLException {
        userService.addUser(password);
    }
}
