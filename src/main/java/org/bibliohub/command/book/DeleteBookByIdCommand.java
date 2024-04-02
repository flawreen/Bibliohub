package org.bibliohub.command.book;

public class DeleteBookByIdCommand extends BookCommand {
    private String password;
    private long id;

    public DeleteBookByIdCommand(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        bookService.deleteBookById(id, password);
    }
}
