package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public interface Filter {
    boolean satisfies(Book book);
}
