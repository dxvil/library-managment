import java.util.ArrayList;
import java.util.UUID;

public class AuthorController {
    static ArrayList<Author> authors = new ArrayList<Author>();
    
    public Author findAuthor(UUID id, String name) {
        Author foundedAuthor = null;
        for(Author author:authors) {
            if(id == null && (author.name == name || author.name.contains(name.toLowerCase()))) {
                foundedAuthor = author;
                break;
            } else if(id != null && name == "" && author.id == id) {
                foundedAuthor = author;
                break;
            }
        }
        return foundedAuthor;
    }

    public void createAuthor(User user, String name) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author newAuthor = new Author(name);

            authors.add(newAuthor);

            return;
        }
        System.out.println("You have no rights to create a new author.");
    }

    public void deleteAuthor(User user, String name) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author author = findAuthor(null, name);

            if(author != null) {
                authors.remove(author);
                return;
            }

            System.out.println("Author is not found");
        }
        System.out.println("You have no rights to delete an author.");
    }

    public void editAuthor(User user, UUID id, String name) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author author = findAuthor(id, "");
            if(author != null) {
                author.editAuthor(name);
                return;
            }

            System.out.println("Author is not found");
        }

        System.out.println("You have no rights to edit an author.");
    }
}