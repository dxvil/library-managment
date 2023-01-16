import models.Author;
import models.Category;
import models.Library;
import models.Role;
import models.User;

public class LibraryManagmentRunner extends Library {
    public static void main(String[] args) {
        Library library = new Library();
        User admin = library.users.create("admin", Role.ADMIN);
        User user = library.users.create("user", Role.USER);

        String adventuresCategoryTitle = "Adventures";
        String Roaling = "Joahn Roaling";
        String bookTitle = "Harry Potter 1";
        String bookDescr = "A book about wizard";

        library.getCategories().create(admin, adventuresCategoryTitle);

        Category adventuresCategory = library.categories.find(null, adventuresCategoryTitle);
        library.authors.create(admin, adventuresCategoryTitle);

        Author author = library.getAuthors().find(null, Roaling);

        library.getBooks().create(admin, bookTitle, bookDescr, adventuresCategory, author);

        library.welcome();
    }
}
