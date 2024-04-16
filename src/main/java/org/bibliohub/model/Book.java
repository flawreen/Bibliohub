package org.bibliohub.model;

import java.util.ArrayList;

public abstract class Book {
    protected int id;
    protected String title;
    protected String author;

    private static int nextId = 1;

    protected String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    protected Shelf shelf;

    protected Wishlist wishlist;

    public Book() {

    }

    public static int getNextId() {
        return nextId;
    }

    public Book(int id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        nextId++;
    }

    public Book(int id, String title, String author, String isbn, Shelf shelf, Wishlist wishlist) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.shelf = shelf;
        this.wishlist = wishlist;
        nextId++;
    }


    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "/___________________" + this.id + "_____________________\\" + "\n" +
                "| Title: " + this.getTitle() + "\n" +
                "| Author: " + this.getAuthor() + "\n" +
                "\\_________________________________________/";
    }
}
