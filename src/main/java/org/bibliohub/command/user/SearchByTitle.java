package org.bibliohub.command.user;


public class SearchByTitle extends User {
    private String title;
    public SearchByTitle(String title) {
        this.title = title;
    }

    @Override
    public void execute() {
        userService.searchBookByTitle(title);
    }

}
