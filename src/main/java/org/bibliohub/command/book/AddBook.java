package org.bibliohub.command.book;


public class AddBook extends Book {
    private String password;
    public AddBook(String password) {
        this.password = password;
    }
    @Override
    public void execute() {
        bookService.addBook(password);
    }


}
