package org.bibliohub.command.user;

public class DeleteUserById extends User {
    private String password;
    private long id;
    public DeleteUserById(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        userService.deleteUser(password, id);
    }

}
