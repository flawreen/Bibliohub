package org.bibliohub.factory;

import org.bibliohub.model.Book;
import org.bibliohub.model.EBook;
import org.bibliohub.model.PhysicalBook;

import java.util.Scanner;

public interface BookFactory {

    static Book createBook(String type) {
        Scanner read = new Scanner(System.in);
        System.out.print("Id: ");
        long id = read.nextLong();
        System.out.print("\nTitle: ");
        String title = read.next();
        System.out.print("\nAuthor: ");
        String author = read.next();
        System.out.print("\nISBN: ");
        String isbn = read.next();

        if (type.equals("EBook")) {
            System.out.println("EBook format:\n1) pdf\n2) epub");
            StringBuilder format = new StringBuilder();
            int option = read.nextInt();

            if (option == 1) format.append("pdf");
            else if (option == 2) format.append("epub");
            else {
                System.out.println("Failed to create book");
                return null;
            }

            return new EBook(id, title, author, isbn, format.toString());

        } else if (type.equals("Physical")) {
            System.out.println("Book cover type:\n1) hardcover\n2) paperback");
            StringBuilder cover = new StringBuilder();
            int option = read.nextInt();

            if (option == 1) cover.append("hardcover");
            else if (option == 2) cover.append("paperback");
            else {
                System.out.println("Failed to create book");
                return null;
            }

            return new PhysicalBook(id, title, author, isbn, cover.toString());

        }

        System.out.println("Failed to create book");
        return null;
    }
}