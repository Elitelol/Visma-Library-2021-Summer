package com.example.VismaLibrary2021.DataStructures.Filters;

import com.example.VismaLibrary2021.DataStructures.Book;

public class AvailabilityFilter implements Filter {
    public boolean taken;

    public AvailabilityFilter(boolean taken) {
        this.taken = taken;
    }

    @Override
    public boolean satisfies(Book book) {
        return book.isTaken() == taken;
    }
}
