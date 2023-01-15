package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import models.Role;
import models.User;


public class UserControllerTests extends UserController{
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    UserController userController = new UserController();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @Order(1)
    @DisplayName("Create a new user with admin rights")
    void createUserWithAdminRights() {
        User adminUser = userController.createUser("Nick", Role.ADMIN);

        assertEquals(Role.ADMIN, adminUser.role);
    }

    @Test
    @Order(2)
    @DisplayName("Create a new user with user rights")
    void createUserWithUserRights() {
        User user = userController.createUser("Nick", Role.USER);

        assertEquals(Role.USER, user.role);
    }

    @Test
    @Order(3)
    @DisplayName("Create a new user with the same name")
    void createUserWithTheSameUserName() {
        userController.createUser("Nick", Role.USER);
        User user2 = userController.createUser("Nick", Role.USER);


        assertNull(user2);
        assertEquals("User with this name is already exist", 
        outputStreamCaptor.toString().trim());
    }

    @Test
    @Order(4)
    @DisplayName("Find a user")
    void findUser() {
        String username = "Nick";
        userController.createUser(username, Role.USER);

        User user = userController.findUser(username);

        assertNotNull(user);
        assertEquals(username, user.name);
    }

    @Test
    @Order(5)
    @DisplayName("Find a non-exist user")
    void findNonExistUser() {
        String username = "bob";

        User user = userController.findUser(username);

        assertNull(user);
    }
}
