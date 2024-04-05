package org.bibliohub.command.user;

public class PrintUsersCommand extends UserCommand {
    public PrintUsersCommand() {
    }

    @Override
    public void execute() {
        userService.printUsersLn();
    }
}
