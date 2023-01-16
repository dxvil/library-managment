package controllers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Author;
import models.Role;
import models.User;

class AuthorControllerTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    String authorName = "Joan Roaling";
    AuthorController authorController = new AuthorController();
    ArrayList<Author> authors = authorController.getAuthors();
    
    User admin = new User("alex", Role.ADMIN);
    User user = new User("morro", Role.USER);
    
    
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        clearList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    void clearList() {
        authors.clear();
    }

    @Test
    @Order(1)
    @DisplayName("Add a new author with admin rights")
    void createAuthorAsAdmin() {
        authorController.createAuthor(admin, authorName);

        assertEquals(1, authors.size());
    }

    @Test
    @Order(2)
    @DisplayName("Add a new author with an user rights")
    void createAuthorAsUser() {
        authorController.createAuthor(user, "Joan Roaling");

        assertEquals("You have no rights to create a new author.", 
        outputStreamCaptor.toString().trim());
        assertEquals(0, authors.size());
    }

    @Test
    @Order(3)
    @DisplayName("Edit author with admin rights")
    void editAuthorAsAdmin() {
        createAuthorAsAdmin();
        assertEquals(1, authors.size());
        
        Author foundedAuthor = authorController.findOne(null, authorName);
        String newName = "Angelina Stefanik";
        authorController.editAuthor(admin, foundedAuthor.id, newName);
        
        
        assertFalse(foundedAuthor == null);
        assertNotEquals(authorName, foundedAuthor.name);
    }

    @Test
    @Order(4)
    @DisplayName("Edit author with user rights")
    void editAuthorAsUser() {
        createAuthorAsAdmin();

        Author foundedAuthor = authorController.findOne(null, authorName);
        String newName = "Angelina Stefanik";

        authorController.editAuthor(user, foundedAuthor.id, newName);

        assertEquals(1, authors.size());
        assertFalse(foundedAuthor == null);
        assertEquals(authorName, foundedAuthor.name);
        assertEquals("You have no rights to edit an author.", 
        outputStreamCaptor.toString().trim());

        authorController.deleteOne(admin, foundedAuthor.id, authorName);
    }

    @Test
    @Order(5)
    @DisplayName("Find an author")
    void findAuthor() {
        createAuthorAsAdmin();
        
        Author foundedAuthor = authorController.findOne(null, authorName);

        assertEquals(authorName, foundedAuthor.name);
    }

    @Test
    @Order(6)
    @DisplayName("Find an non-exist author")
    void findNonExistAuthor() {
        Author foundedAuthor = authorController.findOne(null, "Some name");
        
        assertTrue(foundedAuthor == null);
    }

   

    @Test
    @Order(7)
    @DisplayName("Delete author with user rights")
    void deleteAuthorAsUser() {
        createAuthorAsAdmin();

        Author foundedAuthor = authorController.findOne(null, authorName);

        authorController.deleteOne(user, foundedAuthor.id, foundedAuthor.name);

        assertEquals("You have no rights to delete an author.", 
        outputStreamCaptor.toString().trim());

        assertEquals(1, authors.size());
    }

    @Test
    @Order(8)
    @DisplayName("Delete author with admin rights")
    void deleteAuthorAsAdmin() {
        createAuthorAsAdmin();

        Author foundedAuthor = authorController.findOne(null, authorName);

        authorController.deleteOne(admin, foundedAuthor.id, "");
       
        assertEquals(0, authors.size());
    }
}