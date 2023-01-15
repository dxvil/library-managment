package controllers;
import java.util.ArrayList;

import models.Role;
import models.User;

public class UserController {
    private ArrayList<User> users = new ArrayList<User>();

    public User createUser(String name, Role role) {
        User user = null;
        User userExist = findUser(name);

        if(userExist != null) {
            System.out.println("User with this name is already exist");
            return null;
        }

        user = new User(name, role);
        users.add(user);

        return user;
    }

    public User findUser(String name) {
        User foundedUser = null;
        for(User user:users) {
            if(user.name == name) {
                foundedUser = user;
                break;
            }
        }
        return foundedUser;
    }
}
