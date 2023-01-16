package controllers;
import java.util.ArrayList;
import java.util.UUID;

import interfaces.AuthorInterface;
import interfaces.LibraryInterface;
import models.Author;
import models.Role;
import models.User;

public class AuthorController implements AuthorInterface<Author> {
    static ArrayList<Author> authors = new ArrayList<Author>();

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public static void setAuthors(ArrayList<Author> newAuthorsList) {
        authors = newAuthorsList;
    } 

    public Author find(UUID id, String title) {
        Author foundedAuthor = null;
        for(Author author:authors) {
            if(id == null && (author.name == title || author.name.contains(title.toLowerCase()))) {
                foundedAuthor = author;
                break;
            } else if(id != null && title == "" && author.id == id) {
                foundedAuthor = author;
                break;
            }
        }
        return foundedAuthor;
    }
    
    public void create(User user, String name) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author newAuthor = new Author(name);

            authors.add(newAuthor);

            return;
        }
        System.out.println("You have no rights to create a new author.");
    }

    public void edit(User user, UUID id, String name) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author author = find(id, "");
            if(author != null) {
                author.editAuthor(name);
                return;
            }

            System.out.println("Author is not found");
        }

        System.out.println("You have no rights to edit an author.");
    }

    public void delete(User user, UUID id, String title) {
        if(user != null &&  user.role == Role.ADMIN) { 
            Author author = find(id, title);

            if(author != null) {
                authors.remove(author);
                return;
            }

            System.out.println("Author is not found");
        }
        System.out.println("You have no rights to delete an author.");
    }
}