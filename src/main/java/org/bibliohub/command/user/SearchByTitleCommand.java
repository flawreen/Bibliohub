package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class SearchByTitleCommand extends UserCommand {
    public SearchByTitleCommand(User user) {
        super(user);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(long bookId) {

    }

    @Override
    public void execute(String title) {
        userService.searchBookByTitle(title);
    }
}
