package controllers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Author;
import models.Role;
import models.User;

class AuthorControllerTests {
    String authorName = "Joan Roaling";
    AuthorController authorController = new AuthorController();
    ArrayList<Author> authors = AuthorController.authors;
    User admin = new User("alex", Role.ADMIN);
    User user = new User("morro", Role.USER);
    
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Add a new author with admin rights")
    void createAuthorAsAdmin() {
        authorController.createAuthor(admin, authorName);

        assertEquals(1, authors.size());
    }

    @Test
    @DisplayName("Add a new author with an user rights")
    void createAuthorAsUser() {
        authorController.createAuthor(user, "Joan Roaling");
        assertEquals("You have no rights to create a new author.", 
        outputStreamCaptor.toString().trim());
        assertEquals(1, authors.size());
    }
    @Test
    @DisplayName("Edit author with admin rights")
    void editAuthorWithAdmin() {
        authorController.createAuthor(admin, authorName);
        Author foundedAuthor = authorController.findAuthor(null, authorName);
        String newName = "Angelina Stefanik";
        authorController.editAuthor(admin, foundedAuthor.id, newName);
        
        assertFalse(foundedAuthor == null);
        assertNotEquals(authorName, foundedAuthor.name);
        authorController.deleteAuthor(admin, newName);
    }

    @Test
    @DisplayName("Edit author with user rights")
    void editAuthorWithUser() {
        authorController.createAuthor(admin, authorName);
        Author foundedAuthor = authorController.findAuthor(null, authorName);
        String newName = "Angelina Stefanik";
        authorController.editAuthor(user, foundedAuthor.id, newName);
        
        assertFalse(foundedAuthor == null);
        assertEquals(authorName, foundedAuthor.name);
        assertEquals("You have no rights to edit an author.", 
        outputStreamCaptor.toString().trim());

        authorController.deleteAuthor(admin, authorName);
    }

    @Test
    @DisplayName("Find an author")
    void findAuthor() {
        Author foundedAuthor = authorController.findAuthor(null, authorName);

        assertEquals(authorName, foundedAuthor.name);
    }

    @Test
    @DisplayName("Find an non-exist author")
    void findNonExistAuthor() {
        Author foundedAuthor = authorController.findAuthor(null, "Some name");
        
        assertTrue(foundedAuthor == null);
    }

   

    @Test
    @DisplayName("Delete author with user rights")
    void deleteAuthorWithUser() {
        authorController.deleteAuthor(user, authorName);

        assertEquals("You have no rights to delete an author.", 
        outputStreamCaptor.toString().trim());

        assertEquals(1, authors.size());
    }

    @Test
    @DisplayName("Delete author with admin rights")
    void deleteAuthorWithAdmin() {
        authorController.deleteAuthor(admin, authorName);
       
        assertEquals(0, authors.size());
    }
}