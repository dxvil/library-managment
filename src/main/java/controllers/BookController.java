package controllers;
import java.util.ArrayList;
import java.util.UUID;

import interfaces.BookInterface;
import models.Author;
import models.Book;
import models.Category;
import models.Role;
import models.User;

public class BookController implements BookInterface<Book> {
    public ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> newBooksList) {
        this.books = newBooksList;
    }

    public Book find(UUID id, String title){
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
    
    public void edit(User user, UUID id, String title, String description, Category categoryId, Author author) {
        if(user != null && user.role == Role.ADMIN) {
             Book book = find(id, "");
             if(book != null) {
                 book.updateBook(id, title, description, categoryId, author);
                 return;
             }
             System.out.println("Book is not found");
        }
        System.out.println("You have no rights to update a book.");
     }
 
     public void delete(User user, UUID id, String title) {
         if(user != null && user.role == Role.ADMIN) {
             Book book = find(id, title);
             if(book != null) {
                 books.remove(book);
                 return;
             }
             System.out.println("Book is not found");
         }
         System.out.println("You have no rights to delete a book.");
     }
 
     public void create(User user, String title, String description, Category categoryId, Author author) {
         if(user != null &&  user.role == Role.ADMIN) {
             Book book = new Book(title, description, categoryId, author);
             books.add(book);
 
             book.toString();
             return;
         }
         System.out.println("You have no rights to create a new book.");
        
     }
 
     public Book getBook(UUID id, String title) {
         if(id != null && (title == null || title == "")) {
             return find(id, "");
         }
 
         return find(null, title);
     }
}
