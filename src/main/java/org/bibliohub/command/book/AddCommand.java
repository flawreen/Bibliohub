package org.bibliohub.command.book;


public class AddCommand extends BookCommand {
    AddCommand() {

    }

    @Override
    public void execute(String password) {
        bookService.addBook(password);
    }

    @Override
    public void execute(String password, long id) {

    }
}
