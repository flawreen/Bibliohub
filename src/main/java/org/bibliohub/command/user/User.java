package org.bibliohub.command.user;

import org.bibliohub.interfaces.Command;
import org.bibliohub.service.UserService;

public abstract class User implements Command {
    protected org.bibliohub.model.User user;
    protected static final UserService userService = UserService.getInstance();

    User(org.bibliohub.model.User user) {
        this.user = user;
    }

    User() {

    }

    public abstract void execute();

}
