package controllers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Author;
import models.Book;
import models.Category;
import models.Role;
import models.User;

public class BookControllerTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Author tolkienAuthor = new Author("Tolkien");
    Category adventuresCategory = new Category("Adventures");
    String bookTitle = "Lord of Rings";
    String bookDescr = "Adventures book";

    BookController bookController = new BookController();
    ArrayList<Book> books = bookController.getBooks();

    User admin = new User("alex", Role.ADMIN);
    User user = new User("morro", Role.USER);
    
    
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        clearList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    void clearList() {
        books.clear();
    }

    @Test
    @Order(1)
    @DisplayName("Add a new book with admin rights")
    void createBookAsAdmin() {
        bookController.createBook(
            admin, 
            bookTitle, 
            bookDescr, 
            adventuresCategory, 
            tolkienAuthor
        );

        assertEquals(1, books.size());
    }

    @Test
    @Order(2)
    void getABook() {
        createBookAsAdmin();

        Book book = bookController.getBook(null, bookTitle);
        assertEquals(1, books.size());
        assertTrue(book != null);
    }

    @Test
    @Order(3)
    void findNewBook() {
        createBookAsAdmin();

        Book book = bookController.findOne(null, bookTitle);

        assertEquals(1, books.size());
        assertTrue(book != null);
    }

    @Test
    @Order(4)
    @DisplayName("Find an non-exist book")
    void findNonExistBook() {
        Book foundedBook = bookController.findOne(UUID.randomUUID(), "");
        
        assertTrue(foundedBook == null);
    }

    @Test
    @Order(5)
    @DisplayName("Add a new book with a user rights")
    void createBookAsUser() {
        bookController.createBook(
            user, 
            bookTitle, 
            bookDescr, 
            adventuresCategory, 
            tolkienAuthor
        );

        assertEquals("You have no rights to create a new book.", 
        outputStreamCaptor.toString().trim());

        assertEquals(0, books.size());
    }

    @Test
    @DisplayName("Edit book with admin rights")
    void editBookAsAdmin() {
        createBookAsAdmin();

        Book book = bookController.findOne(null, bookTitle);

        bookController.editBook(admin, book.id, "Lord Of Rings 2", bookDescr, adventuresCategory, tolkienAuthor);
        
        assertFalse(book == null);
        assertNotEquals(bookTitle, book.title);
    }

    @Test
    @Order(6)
    @DisplayName("Edit book with user rights")
    void editBookAsUser() {
        createBookAsAdmin();

        Book book = bookController.findOne(null, bookTitle);

        bookController.editBook(user, book.id, "Lord Of Rings 2", bookDescr, adventuresCategory, tolkienAuthor);
        
        assertEquals(bookTitle, book.title);

        assertEquals("You have no rights to update a book.", 
        outputStreamCaptor.toString().trim());
    }

    @Test
    @Order(7)
    @DisplayName("Delete book with user rights")
    void deleteBookAsUser() {
        createBookAsAdmin();

        Book book = bookController.findOne(null, bookTitle);

        bookController.deleteOne(user, book.id, book.title);

        assertEquals("You have no rights to delete a book.", 
        outputStreamCaptor.toString().trim());

        assertEquals(1, books.size());
    }

    @Test
    @Order(8)
    @DisplayName("Delete book with admin rights")
    void deleteBookAsAdmin() {
        createBookAsAdmin();
        
        Book book = bookController.findOne(null, bookTitle);

        bookController.deleteOne(admin, book.id, book.title);
       
        assertEquals(0, books.size());
    }
}
