package org.bibliohub.command.user;

import java.sql.SQLException;

public class DeleteUserById extends User {
    private String password;
    private long id;
    public DeleteUserById(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        userService.deleteUser(password, id);
    }

}
