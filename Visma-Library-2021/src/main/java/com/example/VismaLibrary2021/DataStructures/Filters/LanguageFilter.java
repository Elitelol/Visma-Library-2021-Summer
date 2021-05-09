package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class LanguageFilter implements Filter{
    private final String language;

    public LanguageFilter(String language) {
        this.language = language;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.getLanguage().equals(language);
    }
}
