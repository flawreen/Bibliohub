package org.bibliohub.command.user;

import org.bibliohub.model.User;
import org.bibliohub.service.UserService;

public abstract class UserCommand {
    protected User user;
    protected static final UserService userService = UserService.getInstance();

    UserCommand(User user) {
        this.user = user;
    }

    UserCommand() {

    }

    public abstract void execute();

    public abstract void execute(long bookId);

    public void execute(String param) {};
}
