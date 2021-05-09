package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class ISBNFilter implements Filter {
    private final String isbn;

    public ISBNFilter(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.getIsbn().equals(isbn);
    }
}
