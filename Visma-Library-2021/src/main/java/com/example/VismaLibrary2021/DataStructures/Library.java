package com.example.VismaLibrary2021.DataStructures;

import com.example.VismaLibrary2021.JsonIO.JSONReaderWriter;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<User> libraryUsers;

    public Library() {
        this.books = JSONReaderWriter.getBooksFromFile();
        this.libraryUsers = new ArrayList<>();
    }

    public Library(List<Book> books){
        this.books = books;
        this.libraryUsers = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book) {
        removeBookFromUser(book);
        books.removeIf(libraryBook -> libraryBook.getGuid() == book.getGuid());
    }

    private void removeBookFromUser(Book book){
        for(User user : libraryUsers){
            if(user.removeBook(book)){
                break;
            }
        }
    }

    public void addUser(User user){
        libraryUsers.add(user);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(String guid){
        for(Book book : books){
            if(book.checkGUID(guid)){
                return book;
            }
        }

        return null;
    }

    public User getUser(String name){
        for(User user : libraryUsers){
            if(user.getName().equals(name)){
                return user;
            }
        }

        return null;
    }
}
