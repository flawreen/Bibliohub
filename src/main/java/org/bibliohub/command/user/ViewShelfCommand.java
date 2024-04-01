package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class ViewShelfCommand extends UserCommand {
    public ViewShelfCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {
        userService.viewShelf(user.getShelfId());
    }

}
