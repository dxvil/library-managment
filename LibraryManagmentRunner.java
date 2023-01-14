import models.Author;
import models.Category;
import models.Library;
import models.Role;
import models.User;

public class LibraryManagmentRunner extends Library {
    public static void main(String[] args) {
        Library library = new Library();
        User user = library.users.createUser("admin", Role.ADMIN);
        String adventuresCategoryTitle = "Adventures";
        library.categories.createCategory(user, adventuresCategoryTitle);
        
        Category adventuresCategory = library.categories.findCategory(null, adventuresCategoryTitle);
        Author rouling = library.authors.findAuthor(null, "Rouling");

        library.books.createBook(user, "Harry Potter 1", "A book about wizard", adventuresCategory, rouling);
        library.welcome();
    }
}
