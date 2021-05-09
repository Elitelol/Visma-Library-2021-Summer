package com.example.VismaLibrary2021.Services;

import com.example.VismaLibrary2021.DataStructures.Book;
import com.example.VismaLibrary2021.DataStructures.Filters.*;
import com.example.VismaLibrary2021.DataStructures.Library;
import com.example.VismaLibrary2021.DataStructures.User;
import com.example.VismaLibrary2021.DataStructures.UserForm;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final Library library;

    public BookService(Library library){
        this.library = library;
    }

    public String addBook(Book book){
        library.addBook(book);
        return "Book added";
    }

    public Book getBook(String guid){
        return library.getBook(guid);
    }

    public String takeBook(UserForm userForm){
        String name = userForm.getName();
        LocalDate periodDate = LocalDate.parse(userForm.getPeriodDate());
        Book book = library.getBook(userForm.getGuid());
        LocalDate currentDate = LocalDate.now();
        User user = library.getUser(name);

        if(user == null){
            user = new User(name);
            library.addUser(user);
        }
        if(book == null){
            return "Book doesn't exist";
        }
        if(book.isTaken()){
            return "Book is already taken";
        }
        if(user.checkTakenBookSize()){
            return "You have taken too much books";
        }
        if(currentDate.isBefore(periodDate) && currentDate.until(periodDate, ChronoUnit.MONTHS) <= 2){
            book.setTaken(true);
            user.takeBook(book);

            return "You have taken the book";
        }

        return "Invalid period date";
    }

    public List<Book> getBooksByAuthor(String author){
        Filter filter = new AuthorFilter(author);

        return getBooksByFilter(filter);
    }

    public List<Book> getBooksByCategory(String category){
        Filter filter = new CategoryFilter(category);

        return getBooksByFilter(filter);
    }

    public List<Book> getBooksByLanguage(String language){
        Filter filter = new LanguageFilter(language);

        return getBooksByFilter(filter);
    }

    public List<Book> getBooksByISBN(String isbn){
        Filter filter = new ISBNFilter(isbn);

        return getBooksByFilter(filter);
    }

    public List<Book> getBooksByName(String name){
        Filter filter = new NameFilter(name);

        return getBooksByFilter(filter);
    }

    public List<Book> getBooksByAvailability(boolean taken){
        Filter filter = new AvailabilityFilter(taken);

        return getBooksByFilter(filter);
    }

    private List<Book> getBooksByFilter(Filter filter){
        List<Book> books = library.getBooks();
        List<Book> filteredBooks = new ArrayList<>();

        for(Book book : books){
            if(filter.satisfies(book)){
                filteredBooks.add(book);
            }
        }

        return filteredBooks;
    }

    public String deleteBook(String guid){
        Book book = library.getBook(guid);

        if(book == null){
            return "Book doesn't exist";
        }

        library.removeBook(book);

        return "Book removed";
    }
}
