package org.bibliohub.model;

import java.util.ArrayList;

public abstract class Book {
    protected long id;
    protected String title;
    protected String author;

    private ArrayList<Shelf> shelves;

    private ArrayList<Wishlist> wishlists;

    public Book() {

    }

    public Book(long id, String title, String author, ArrayList<Shelf> shelves, ArrayList<Wishlist> wishlists) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.shelves = shelves;
        this.wishlists = wishlists;
    }

    public ArrayList<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(ArrayList<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(ArrayList<Shelf> shelves) {
        this.shelves = shelves;
    }

    public Book(long id, String title, String author, ArrayList<Shelf> shelves) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.shelves = shelves;
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
