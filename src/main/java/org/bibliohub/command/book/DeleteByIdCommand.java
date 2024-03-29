package org.bibliohub.command.book;

public class DeleteByIdCommand extends BookCommand {
    DeleteByIdCommand() {
    }

    @Override
    public void execute(String password) {

    }

    @Override
    public void execute(String password, long id) {
        bookService.deleteBookById(id, password);
    }
}
