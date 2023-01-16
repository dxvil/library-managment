package controllers;
import java.util.ArrayList;

import models.Role;
import models.User;

public class UserController {
    private ArrayList<User> users = new ArrayList<User>();

    private ArrayList<User> getUsers() {
        return users;
    }

    private void setUsers(ArrayList<User> newUsersList) {
        this.users = newUsersList;
    }

    public User create(String name, Role role) {
        User user = null;
        User userExist = find(name);

        if(userExist != null) {
            System.out.println("User with this name is already exist");
            return null;
        }

        user = new User(name, role);
        users.add(user);

        return user;
    }

    public User find(String name) {
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
