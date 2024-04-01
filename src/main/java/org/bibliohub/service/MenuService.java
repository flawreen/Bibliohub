package org.bibliohub.service;

import org.bibliohub.command.user.ViewAvailableBooksCommand;
import org.bibliohub.interfaces.Command;

public class MenuService {
    public static void executeCommand(Command command) {
        command.execute();
    }

    public static void main(String[] args) {
        executeCommand(new ViewAvailableBooksCommand());
    }

}
