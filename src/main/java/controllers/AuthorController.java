package controllers;
import java.util.ArrayList;
import java.util.UUID;

import interfaces.AuthorInterface;
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

    public Author findOne(UUID id, String title) {
        Author foundedAuthor = null;
        for(Author author:authors) {
            boolean withTitle = title != null && title != "";
            boolean withId = id != null;

            if(!withId && (author.name == title || author.name.contains(title.toLowerCase()))) {
                foundedAuthor = author;
                break;
            } else if(!withTitle && withId && author.id == id) {
                foundedAuthor = author;
                break;
            }
        }
        return foundedAuthor;
    }
    
    public void createAuthor(User user, String name) {
        boolean isAdmin = user != null &&  user.role == Role.ADMIN;

        if(isAdmin) { 
            Author newAuthor = new Author(name);

            authors.add(newAuthor);

            return;
        }
        System.out.println("You have no rights to create a new author.");
    }

    public void editAuthor(User user, UUID id, String name) {
        boolean isAdmin = user != null &&  user.role == Role.ADMIN;

        if(isAdmin) { 
            Author author = findOne(id, "");
            
            if(author != null) {
                System.out.println("author" + author);
                author.editAuthor(name);
                return;
            }

            System.out.println("Author is not found");
            return;
        }

        System.out.println("You have no rights to edit an author.");
    }

    public void deleteOne(User user, UUID id, String title) {
        boolean isAdmin = user != null &&  user.role == Role.ADMIN;

        if(isAdmin) { 
            Author author = findOne(id, title);

            if(author != null) {
                authors.remove(author);
                return;
            }

            System.out.println("Author is not found");
            return;
        }
        System.out.println("You have no rights to delete an author.");
    }
}