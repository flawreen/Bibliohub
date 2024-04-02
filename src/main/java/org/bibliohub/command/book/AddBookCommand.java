package org.bibliohub.command.book;


public class AddBookCommand extends BookCommand {
    private String password;
    public AddBookCommand(String password) {
        this.password = password;
    }
    @Override
    public void execute() {
        bookService.addBook(password);
    }


}
