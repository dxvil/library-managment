package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import models.Category;
import models.Role;
import models.User;

public class CategoryControllerTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    String categoryTitle = "Adventures";

    CategoryController categoryController = new CategoryController();
    ArrayList<Category> categories = categoryController.categories;

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
        categories.clear();
    }

    @Test
    @Order(1)
    @DisplayName("Create a new category with admin rights")
    void createCategoryAsAdmin() {
        categoryController.createCategory(admin, categoryTitle);

        assertEquals(1, categories.size());
    }

    @Test
    @Order(2)
    @DisplayName("Create a new category with user rights")
    void createCategoryAsUser() {
        categoryController.createCategory(user, categoryTitle);

        assertEquals(0, categories.size());
        assertEquals("You have no rights to create a new category.", 
        outputStreamCaptor.toString().trim());
    }

    @Test
    @Order(3)
    @DisplayName("Find a  category")
    void findCategory() {
        createCategoryAsAdmin();

        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        assertNotNull(foundedCategory);
        assertEquals(categoryTitle, foundedCategory.name);
    }

    @Test
    @Order(4)
    @DisplayName("Find a non-exist category")
    void findNonExistCategory() {
        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        assertNull(foundedCategory);
        assertEquals(0, categories.size());
    }

    @Test
    @Order(5)
    @DisplayName("Edit a category with admin rights")
    void editCategoryAsAdmin() {
        createCategoryAsAdmin();

        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        categoryController.editCategory(admin, "Romance", foundedCategory.id);

        assertNotEquals(categoryTitle, foundedCategory.name);
    }

    @Test
    @Order(6)
    @DisplayName("Edit a category with user rights")
    void editCategoryAsUser() {
        createCategoryAsAdmin();

        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        categoryController.editCategory(user, "Romance", foundedCategory.id);

        assertEquals(categoryTitle, foundedCategory.name);
        assertEquals("You have no rights to edit a category.", 
        outputStreamCaptor.toString().trim());
    }

    @Test
    @Order(7)
    @DisplayName("Delete a category with user rights")
    void deleteCategoryAsUser() {
        createCategoryAsAdmin();

        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        categoryController.deleteCategory(user, foundedCategory.id);


        assertEquals(1, categories.size());
        assertEquals("You have no rights to delete a category.", 
        outputStreamCaptor.toString().trim());
    }

    @Test
    @Order(8)
    @DisplayName("Delete a category wth admin rights")
    void deleteCategoryAsAdmin() {
        createCategoryAsAdmin();

        Category foundedCategory = categoryController.findCategory(null, categoryTitle);

        categoryController.deleteCategory(admin, foundedCategory.id);


        assertEquals(0, categories.size());
    }
}
