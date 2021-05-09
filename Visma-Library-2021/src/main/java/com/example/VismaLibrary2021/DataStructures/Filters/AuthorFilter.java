package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class AuthorFilter implements Filter {
    private final String author;

    public AuthorFilter(String author){
        this.author = author;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.getAuthor().equals(author);
    }
}
