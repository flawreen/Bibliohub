package org.bibliohub.model;

import java.util.ArrayList;

public abstract class Book {
    protected long id;
    protected String title;
    protected String author;

    protected String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    protected ArrayList<Shelf> shelves;

    protected ArrayList<Wishlist> wishlists;

    public Book() {

    }

    public Book(long id, String title, String author, String isbn, ArrayList<Shelf> shelves, ArrayList<Wishlist> wishlists) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelves = shelves;
        this.wishlists = wishlists;
    }

    public Book(long id, String title, String author, ArrayList<Wishlist> wishlists, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.wishlists = wishlists;
    }

    public Book(long id, String title, String author, String isbn, ArrayList<Shelf> shelves) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelves = shelves;
    }

    public ArrayList<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(ArrayList<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }


    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(ArrayList<Shelf> shelves) {
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
        return "____________________" + this.id + "______________________" + "\n" +
                "| Title: " + this.getTitle() + "\n" +
                "| Author: " + this.getAuthor() + "\n" +
                "\\___________________________________________/" + "\n";
    }
}
