package org.bibliohub.command.user;

public class DeleteUserByIdCommand extends UserCommand {
    private String password;
    private long id;
    public DeleteUserByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        userService.deleteUser(password, id);
    }

}
