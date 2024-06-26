package org.bibliohub.interfaces;

import org.bibliohub.model.Book;

import java.util.ArrayList;

public interface PrintBookArray {
    static String printBooks(ArrayList<Book> bookList) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < bookList.size(); ++i) {
            res.append(bookList.get(i).toString());
            res.append("\n");
        }
        return res.toString();
    }
}
