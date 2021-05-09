package com.example.VismaLibrary2021.Controllers;

import com.example.VismaLibrary2021.DataStructures.Book;
import com.example.VismaLibrary2021.DataStructures.Library;
import com.example.VismaLibrary2021.DataStructures.UserForm;
import com.example.VismaLibrary2021.JsonIO.JSONReaderWriter;
import com.example.VismaLibrary2021.Services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(){
        Library library = new Library();
        bookService = new BookService(library);
    }

    @PostMapping("/book/add")
    public String addBook(@RequestBody Book book){
        JSONReaderWriter.writeToFile(book);
        return bookService.addBook(book);
    }

    @GetMapping("/book/")
    public Book getBook(@RequestParam String guid){
        return bookService.getBook(guid);
    }

    @PutMapping("book/take")
    public String takeBook(@RequestBody UserForm userForm){
        return bookService.takeBook(userForm);
    }

    @GetMapping("book/filterByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam String author){
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("book/filterByCategory")
    public List<Book> getBooksByCategory(@RequestParam String category){
        return bookService.getBooksByCategory(category);
    }

    @GetMapping("book/filterByLanguage")
    public List<Book> getBooksByLanguage(@RequestParam String language){
        return bookService.getBooksByLanguage(language);
    }

    @GetMapping("book/filterByISBN")
    public List<Book> getBooksByISBN(@RequestParam String isbn){
        return bookService.getBooksByISBN(isbn);
    }

    @GetMapping("book/filterByName")
    public List<Book> getBooksByName(@RequestParam String name){
        return bookService.getBooksByName(name);
    }

    @GetMapping("book/filterByAvailability")
    public List<Book> getBooksByAvailability(@RequestParam boolean taken){
        return bookService.getBooksByAvailability(taken);
    }

    @DeleteMapping("book/")
    public String deleteBook(@RequestParam String guid){
        Book book = bookService.getBook(guid);
        String message = bookService.deleteBook(guid);

        if(message.equals("Book removed")){
            JSONReaderWriter.removeBookFromFile(book);
        }

        return message;
    }
}
