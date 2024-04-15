package org.bibliohub.command.book;


public class MakeBookAvailable extends Book {
    private String password;
    private long id;

    public MakeBookAvailable(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        bookService.makeBookAvailable(id, password);
    }

}
