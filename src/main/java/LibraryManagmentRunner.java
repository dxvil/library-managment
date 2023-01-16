import models.Author;
import models.Category;
import models.Library;
import models.Role;
import models.User;

public class LibraryManagmentRunner extends Library {
    public static void main(String[] args) {
        Library library = new Library();
        User admin = library.users.createUser("admin", Role.ADMIN);
        User user = library.users.createUser("user", Role.USER);

        String adventuresCategoryTitle = "Adventures";
        String Roaling = "Joahn Roaling";
        String bookTitle = "Harry Potter 1";
        String bookDescr = "A book about wizard";

        library.getCategories().createCategory(admin, adventuresCategoryTitle);

        Category adventuresCategory = library.categories.findOne(null, adventuresCategoryTitle);
        library.authors.createAuthor(admin, Roaling);

        Author author = library.getAuthors().findOne(null, Roaling);

        library.getBooks().createBook(admin, bookTitle, bookDescr, adventuresCategory, author);

        library.welcomeToTheLibrary();
    }
}
