package com.example.VismaLibrary2021;

import com.example.VismaLibrary2021.DataStructures.Book;
import com.example.VismaLibrary2021.DataStructures.Library;
import com.example.VismaLibrary2021.DataStructures.UserForm;
import com.example.VismaLibrary2021.Services.BookService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookTests extends TestCase {

	@Test
	void addBook() {
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);
		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");

		//act
		String result = bookService.addBook(book);

		//assert
		assertEquals("Book added", result);
	}

	@Test
	void removeBookSuccessful(){
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);
		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		bookService.addBook(book);

		//act
		String result = bookService.deleteBook(book.getGuid().toString());

		//assert
		assertEquals("Book removed", result);
	}

	@Test
	void removeBookNotFound(){
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);
		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		bookService.addBook(book);

		//act
		String result = bookService.deleteBook("asdfksadfl");

		//assert
		assertEquals("Book doesn't exist", result);
	}

	@Test
	void getBookByAuthor(){
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);
		Book book = new Book("name", "tolkien", "category", "lang", "2020-01-01", "isbn");
		Book book1 = new Book("name1", "tolkien", "category", "lang", "2020-01-01", "isbn");
		bookService.addBook(book);
		bookService.addBook(book1);

		//act
		List<Book> booksFound = bookService.getBooksByAuthor("tolkien");

		//assert
		assertEquals(2, booksFound.size());
	}

	@Test
	public void takeBooksExceeded(){
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);
		List<UserForm> userForms = new ArrayList<>();
		String result = "";

		for(int i = 1; i <= 4; i++){
			Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
			String periodDate = LocalDate.now().plusDays(10).toString();
			bookService.addBook(book);
			userForms.add(new UserForm("bob", periodDate, book.getGuid().toString()));
		}

		//act
		for (UserForm userForm : userForms){
			result = bookService.takeBook(userForm);
		}

		//assert
		assertEquals("You have taken too much books", result);
	}

	@Test
	void takeBookDateExceeded(){
		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);

		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		String periodDate = LocalDate.now().plusDays(365).toString();
		bookService.addBook(book);
		UserForm userForm = new UserForm("bob", periodDate, book.getGuid().toString());


		//act
		String result = bookService.takeBook(userForm);

		//assert
		assertEquals("Invalid period date", result);
	}

	@Test
	void takeBookDateBeforeCurrentDate(){

		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);

		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		String periodDate = LocalDate.now().minusDays(20).toString();
		bookService.addBook(book);
		UserForm userForm = new UserForm("bob", periodDate, book.getGuid().toString());

		//act
		String result = bookService.takeBook(userForm);

		//assert
		assertEquals("Invalid period date", result);
	}

	@Test
	void takeBookWhichIsTaken(){

		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);

		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		String periodDate = LocalDate.now().plusDays(20).toString();
		bookService.addBook(book);
		UserForm userForm = new UserForm("bob", periodDate, book.getGuid().toString());

		//act
		bookService.takeBook(userForm);
		String result = bookService.takeBook(userForm);

		//assert
		assertEquals("Book is already taken", result);
	}

	@Test
	void takeBookWhichDoesntExist(){

		//arrange
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);

		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		String periodDate = LocalDate.now().plusDays(20).toString();
		bookService.addBook(book);
		UserForm userForm = new UserForm("bob", periodDate, "omg");

		//act
		bookService.takeBook(userForm);
		String result = bookService.takeBook(userForm);

		//assert
		assertEquals("Book doesn't exist", result);
	}

	@Test
	void takeBookSuccessful(){
		Library library = new Library(new ArrayList<>());
		BookService bookService = new BookService(library);

		Book book = new Book("name", "author", "category", "lang", "2020-01-01", "isbn");
		String periodDate = LocalDate.now().plusDays(20).toString();
		bookService.addBook(book);
		UserForm userForm = new UserForm("bob", periodDate, book.getGuid().toString());

		//act
		String result = bookService.takeBook(userForm);

		//assert
		assertEquals("You have taken the book", result);
	}
}
