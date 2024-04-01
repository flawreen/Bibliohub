package org.bibliohub.command.book;

public class DeleteByIdCommand extends BookCommand {
    private String password;
    private long id;

    public DeleteByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        bookService.deleteBookById(id, password);
    }
}
