package org.bibliohub.command.book;


public class MakeBookAvailableCommand extends BookCommand {
    private String password;
    private long id;

    public MakeBookAvailableCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        bookService.makeBookAvailable(id, password);
    }

}
