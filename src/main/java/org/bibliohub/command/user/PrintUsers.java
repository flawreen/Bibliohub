package org.bibliohub.command.user;

public class PrintUsers extends User {
    public PrintUsers() {
    }

    @Override
    public void execute() {
        userService.printUsersLn();
    }
}
