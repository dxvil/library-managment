package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import controllers.AuthorController;
import controllers.BookController;
import controllers.CategoryController;
import controllers.UserController;

public class LibraryTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    static AuthorController authorController = new AuthorController();
    static UserController userController = new UserController();
    static BookController bookController = new BookController();
    static CategoryController categoryController = new CategoryController();

    Library library = new Library();
    HashMap<UUID, Checkout> borrowedBooks = library.borrowedBooks;
    ArrayList<Checkout> checkoutJournal = library.checkoutJournal;

    static String bookTitle = "Lord Of Rings";

    @BeforeAll
    public static void initAll() {
        setupLibrary();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    static void setupLibrary() {
        User admin = userController.createUser("admin", Role.ADMIN);
        User user = userController.createUser("user", Role.USER);

        categoryController.createCategory(admin, "Adventures");
        Category category = categoryController.findCategory(null, "Adventures");

        authorController.createAuthor(admin, "Tolkien");

        Author author = authorController.findAuthor(null, "Tolkien");

        bookController.createBook(
        admin, bookTitle, "Adventure book", 
        category, author);


    }

    @Test
    @Order(1)
    @DisplayName("Borrow a book from library")
    void borrowBook() {
        User user = userController.findUser("user");
        Book book = bookController.findABook(null, bookTitle);


        library.borrowBook(user, book);

        assertNotNull(borrowedBooks.get(user.id));
        assertEquals(1, borrowedBooks.size());
    }

    @Test
    @Order(2)
    @DisplayName("Borrow a book from library with some empty values")
    void borrowBookWithNonSpecifiedValues() {
        Book book = bookController.findABook(null, bookTitle);

        library.borrowBook(null, book);

        assertEquals("Please, specify a user and a book to borrow from the library.", 
        outputStreamCaptor.toString().trim());

        assertEquals(0, borrowedBooks.size());
    }

    @Test
    @Order(3)
    @DisplayName("Back a book to a library")
    void backBook() {
        borrowBook();
        assertEquals(1, borrowedBooks.size());

        User user = userController.findUser("user");
        Book book = bookController.findABook(null, bookTitle);

        library.backBook(user, book);

        assertEquals(0, borrowedBooks.size());
    }

    @Test
    @Order(4)
    @DisplayName("Back a book to a library with some empty values")
    void backBookWithNonSpecifiedValues() {
        borrowBook();
        assertEquals(1, borrowedBooks.size());

        
        Book book = bookController.findABook(null, bookTitle);

        library.backBook(null, book);

        assertEquals(1, borrowedBooks.size());
    }
}
