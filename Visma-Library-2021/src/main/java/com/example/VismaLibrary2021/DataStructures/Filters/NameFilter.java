package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class NameFilter implements Filter {
    private final String name;

    public NameFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.getName().equals(name);
    }
}
