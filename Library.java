import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Library {
    public AuthorController authors= new AuthorController();
    public UserController users = new UserController();
    public BookController books = new BookController();
    public CategoryController categories = new CategoryController();

    ArrayList<Category> categoriesList = categories.categories;
    ArrayList<Book> booksList = books.books;

    HashMap<UUID, Checkout> borrowedBooks = new HashMap<UUID, Checkout>();
    ArrayList<Checkout> checkoutJournal = new ArrayList<Checkout>();

    public void welcome() {
        System.out.println("Welcome to the library");
        System.out.println("Look at our categories: " + categoriesList.toString());
        System.out.println("Look at our books in stock: " + booksList.toString());
    }

    public void borrowBook(User user, Book book) {
        if(user != null && book != null) {
            LocalDate startTime = LocalDate.now();
            LocalDate endTime = LocalDate.now().plusDays(60);
            Checkout checkout = new Checkout(startTime, endTime, book.id, user.id, false);
            
            checkoutJournal.add(checkout);
            borrowedBooks.put(user.id, checkout);
        }
       System.out.println("Please, specify a user and a book to borrow from the library.");
    }

    public void backBook(User user, Book book) {
        if(user != null && book != null) { 

        }
        System.out.println("Please, specify a user and a book to back a book to the library.");
    }

    public void findCheckoutForBook() {
        
    }
}
