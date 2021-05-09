package com.example.VismaLibrary2021.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<Book> takenBooks;

    public User(String name){
        this.name = name;
        this.takenBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void takeBook(Book book){
        takenBooks.add(book);
    }

    public boolean removeBook(Book book){
        for(Book takenBook : takenBooks){
            if(takenBook.getGuid() == book.getGuid()){
                takenBooks.remove(book);
                return true;
            }
        }

        return false;
    }

    public boolean checkTakenBookSize(){
        return takenBooks.size() >= 3;
    }
}
