package com.example.VismaLibrary2021.DataStructures;

import java.time.LocalDate;
import java.util.UUID;

public class Book {
    private final String name;
    private final String author;
    private final String category;
    private final String language;
    private final LocalDate publication;
    private final String isbn;
    private UUID guid;
    private boolean isTaken;

    public Book(String name, String author, String category, String language, String publication, String isbn) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publication = LocalDate.parse(publication);
        this.isbn = isbn;
        this.guid = UUID.randomUUID();
        this.isTaken = false;
    }

    public boolean checkGUID(String guid){
        return this.guid.toString().equals(guid);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public String getIsbn() {
        return isbn;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
