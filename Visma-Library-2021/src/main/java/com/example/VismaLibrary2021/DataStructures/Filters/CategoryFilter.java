package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class CategoryFilter implements Filter {
    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.getCategory().equals(category);
    }
}
