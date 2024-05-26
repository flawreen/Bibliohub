package org.bibliohub.command.user;

import java.sql.SQLException;

public class PrintUsers extends User {
    public PrintUsers() {
    }

    @Override
    public void execute() throws SQLException {
        userService.printUsersLn();
    }
}
