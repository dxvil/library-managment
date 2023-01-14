import java.util.ArrayList;
import java.util.UUID;

public class Library {
    static ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Author> authors = new ArrayList<Author>();
    static ArrayList<Category> categories = new ArrayList<Category>();

    public Book findABookById(UUID id, String title){
        Book foundedBook = null;
        for(Book book:books){
            if(id != null && book.id == id) {
                foundedBook = book;
                break;
            } else if(
            (title != null || title != "") && 
            (book.title == title || book.title.contains(title)
            )) {
                foundedBook = book;
                break;
            }
        }
        return foundedBook;
    }

    public Category findCategory(UUID id) {
        Category foundedCategory = null;
        if(id != null) {
            for(Category category:categories) {
                if(id == category.id) {
                    foundedCategory = category;
                    break;
                }
            }
            
        }
        return foundedCategory;
    }

    public void updateBook(User user, UUID id, String title, String description, Category categoryId, Author author) {
       if(user != null && user.role == Role.ADMIN) {
            Book book = findABookById(id, "");
            if(book != null) {
                book.updateBook(id, title, description, categoryId, author);
                return;
            }
            System.out.println("Book is not found");
       }
       System.out.println("You have no rights to update a book.");
    }

    public void deleteBook(User user, UUID id) {
        if(user != null && user.role == Role.ADMIN) {
            Book book = findABookById(id, "");
            if(book != null) {
                books.remove(book);
                return;
            }
            System.out.println("Book is not found");
        }
        System.out.println("You have no rights to delete a book.");
    }

    public void createBook(User user, String title, String description, Category categoryId, Author author) {
        if(user != null &&  user.role == Role.ADMIN) {
            Book book = new Book(title, description, categoryId, author);
            books.add(book);

            book.toString();
            return;
        }
        System.out.println("You have no rights to create a new book.");
       
    }

    public Book getABook(UUID id, String title) {
        if(id != null && (title == null || title == "")) {
            return findABookById(id, "");
        }

        return findABookById(null, title);
    }

    public void createCategory(User user, String name) {
        if(user != null && user.role == Role.ADMIN) {
            Category category = new Category(name);
            categories.add(category);

            category.toString();

            return;
        }
        System.out.println("You have no rights to create a new category.");
    }

    public void editCategory(User user, String name, UUID id) {
        if(user != null &&  user.role == Role.ADMIN && id != null) {
            Category category = findCategory(id);

            category.changeCategory(name);
            return;
        }
        System.out.println("You have no rights to edit a category.");
    }

    public void deleteCategory(User user, UUID id) {
        if(user != null &&  user.role == Role.ADMIN) {
            Category category = findCategory(id);
            if(category != null) {
                categories.remove(category);
                return;
            }
            System.out.println("Category is not found");
        }
        System.out.println("You have no rights to delete a category.");
    }

    public  void welcome() {
        System.out.println("Welcome to the library");
        System.out.println("Look at our categories: " + categories.toString());
        System.out.println("Look at our books in stock: " + books.toString());
    }
}
