package org.bibliohub.command.user;


import java.sql.SQLException;

public class SearchByTitle extends User {
    private String title;
    public SearchByTitle(String title) {
        this.title = title;
    }

    @Override
    public void execute() throws SQLException {
        userService.searchBookByTitle(title);
    }

}
