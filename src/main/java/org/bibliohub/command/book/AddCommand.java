package org.bibliohub.command.book;


public class AddCommand extends BookCommand {
    private String password;
    AddCommand(String password) {
        this.password = password;
    }
    @Override
    public void execute() {
        bookService.addBook(password);
    }


}
