package controllers;
import java.util.ArrayList;
import java.util.UUID;

import models.Author;
import models.Book;
import models.Category;
import models.Role;
import models.User;

public class BookController {
    public ArrayList<Book> books = new ArrayList<Book>();

    public Book findABook(UUID id, String title){
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
    
    public void updateBook(User user, UUID id, String title, String description, Category categoryId, Author author) {
        if(user != null && user.role == Role.ADMIN) {
             Book book = findABook(id, "");
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
             Book book = findABook(id, "");
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
             return findABook(id, "");
         }
 
         return findABook(null, title);
     }
}
