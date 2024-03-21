package org.bibliohub.model;

public abstract class Book {
    protected long id;
    protected String title;
    protected String author;

    public Book() {

    }

    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "/----------" + this.id + "---------\\" + "\n" +
"        " + this.title + "        \n" +
"|---------------------|\n" +
"|                     |\n" +
"      " + this.author + "      \n" +
"|                     |\n" +
"|                     |\n" +
"\\---------------------/\n";
    }
}
