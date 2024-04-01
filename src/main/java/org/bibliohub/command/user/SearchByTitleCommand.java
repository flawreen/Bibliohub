package org.bibliohub.command.user;

import org.bibliohub.model.User;

public class SearchByTitleCommand extends UserCommand {
    private String title;
    public SearchByTitleCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute() {
        userService.searchBookByTitle(title);
    }

}
