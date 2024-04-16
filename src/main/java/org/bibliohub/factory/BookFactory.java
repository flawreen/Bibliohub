package org.bibliohub.factory;

import org.bibliohub.model.Book;
import org.bibliohub.model.EBook;
import org.bibliohub.model.PhysicalBook;
import org.bibliohub.repository.BookRepository;
import org.bibliohub.repository.UserRepository;
import org.bibliohub.service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public interface BookFactory {
    static Book createBook() throws Exception {
        Scanner read = new Scanner(System.in);
        long id = Book.getNextId();

        System.out.print("\nTitle: ");
        String title = read.nextLine();

        System.out.print("\nAuthor: ");
        String author = read.nextLine();

        System.out.print("\nISBN: ");
        String isbn = read.nextLine();

        System.out.println("Type:\n1. Physical Book 2. EBook");
        int type = read.nextInt();

        if (type == 2) {
            System.out.println("EBook format:\n1. pdf 2. epub");
            StringBuilder format = new StringBuilder();
            int option = read.nextInt();

            if (option == 1) format.append("pdf");
            else if (option == 2) format.append("epub");
            else throw new Exception("Failed to create book");

            return new EBook(id, title, author, isbn, format.toString());

        } else if (type == 1) {
            System.out.println("Book cover type:\n1. hardcover 2. paperback");
            StringBuilder cover = new StringBuilder();
            int option = read.nextInt();

            if (option == 1) cover.append("hardcover");
            else if (option == 2) cover.append("paperback");
            else throw new Exception("Failed to create book");

            return new PhysicalBook(id, title, author, isbn, cover.toString());

        }

        throw new Exception("Failed to create book");
    }
}
