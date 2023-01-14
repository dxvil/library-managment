import java.util.ArrayList;

public class Library {
    public AuthorController authors= new AuthorController();
    public UserController users = new UserController();
    public BookController books = new BookController();
    public CategoryController categories = new CategoryController();

    ArrayList<Category> categoriesList = categories.categories;
    ArrayList<Book> booksList = books.books;


    public void welcome() {
        System.out.println("Welcome to the library");
        System.out.println("Look at our categories: " + categoriesList.toString());
        System.out.println("Look at our books in stock: " + booksList.toString());
    }

}
