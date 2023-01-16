package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import controllers.AuthorController;
import controllers.BookController;
import controllers.CategoryController;
import controllers.UserController;

public class Library {
    public AuthorController authors = new AuthorController();
    public UserController users = new UserController();
    public BookController books = new BookController();
    public CategoryController categories = new CategoryController();

    ArrayList<Category> categoriesList = categories.categories;
    ArrayList<Book> booksList = books.books;

    protected HashMap<UUID, Checkout> borrowedBooks = new HashMap<UUID, Checkout>();
    protected ArrayList<Checkout> checkoutJournal = new ArrayList<Checkout>();

    public UserController getUsers() {
        return users;
    }

    public BookController getBooks() {
        return books;
    }

    public AuthorController getAuthors() {
        return authors;
    }

    public CategoryController getCategories() {
        return categories;
    }

    public void setUsers(UserController users) {
        this.users = users;
    }

    public void setBooks(BookController books) {
        this.books = books; 
    }

    public void setAuthors(AuthorController authors) {
        this.authors = authors;
    }

    public void setCategories(CategoryController categories) {
        this.categories = categories;
    }

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public ArrayList<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(ArrayList<Book> booksList) {
        this.booksList = booksList;
    }

    public HashMap<UUID, Checkout> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(HashMap<UUID, Checkout> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public ArrayList<Checkout> getCheckoutJournal() {
        return checkoutJournal;
    }

    public void setCheckoutJournal(ArrayList<Checkout> checkoutJournal) {
        this.checkoutJournal = checkoutJournal;
    }

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
            Checkout foundedCheckout = findCheckoutForBook(user, book);

            borrowedBooks.remove(user.id);
            
            if(foundedCheckout != null) {
                foundedCheckout.changeStatus(true);
            }
        }
        System.out.println("Please, specify a user and a book to back a book to the library.");
    }

    private Checkout findCheckoutForBook(User user, Book book) {
        Checkout foundedCheckout = null;
        for(Checkout checkout:checkoutJournal) {
            if(user.id == checkout.userId && book.id == checkout.bookId) {
                foundedCheckout = checkout;
            }
        }
        return foundedCheckout;
    }
}
