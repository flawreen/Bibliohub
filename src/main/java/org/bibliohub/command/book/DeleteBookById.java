package org.bibliohub.command.book;

public class DeleteBookById extends Book {
    private String password;
    private long id;

    public DeleteBookById(String password, long id) {
        this.password = password;
        this.id = id;
    }

    @Override
    public void execute() {
        bookService.deleteBookById(id, password);
    }
}
