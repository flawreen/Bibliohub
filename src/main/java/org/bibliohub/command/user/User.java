package org.bibliohub.command.user;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.UserService;

import java.sql.SQLException;

public abstract class User implements Command {
    protected org.bibliohub.model.User user;
    protected static final UserService userService;

    static {
        try {
            userService = UserService.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    User(org.bibliohub.model.User user) {
        this.user = user;
    }

    User() {

    }

    public abstract void execute() throws SQLException;

}
