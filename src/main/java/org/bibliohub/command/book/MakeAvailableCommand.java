package org.bibliohub.command.book;


public class MakeAvailableCommand extends BookCommand {
    MakeAvailableCommand() {
    }

    @Override
    public void execute(String password) {

    }

    @Override
    public void execute(String password, long id) {
        bookService.makeBookAvailable(id, password);
    }
}
