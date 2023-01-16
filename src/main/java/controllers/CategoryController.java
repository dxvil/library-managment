package controllers;
import java.util.ArrayList;
import java.util.UUID;

import interfaces.LibraryInterface;
import models.Category;
import models.Role;
import models.User;

public class CategoryController implements LibraryInterface<Category> {
    public ArrayList<Category> categories = new ArrayList<Category>();
    
    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> newCategoriesList) {
        this.categories = newCategoriesList;
    }

    public Category findOne(UUID id, String title) {
        Category foundedCategory = null;
            for(Category category:categories) {
                if(id != null && id == category.id) {
                    foundedCategory = category;
                    break;
                } else if(id == null && title != "" && title == category.name) {
                    foundedCategory = category;
                    break;
                }
        }
        return foundedCategory;
    }

    public void createCategory(User user, String name) {
        if(user != null && user.role == Role.ADMIN) {
            Category category = new Category(name);
            
            categories.add(category);

            category.toString();

            return;
        }
        System.out.println("You have no rights to create a new category.");
    }

    public void editCategory(User user, String name, UUID id) {
        if(user != null &&  user.role == Role.ADMIN && id != null) {
            Category category = findOne(id, "");
            if(category != null) {
                category.changeCategory(name);
                return;
            }
            System.out.println("Category is not found");
        }
        System.out.println("You have no rights to edit a category.");
    }

    public void deleteOne(User user, UUID id, String title) {
        if(user != null &&  user.role == Role.ADMIN) {
            Category category = findOne(id, title);
            if(category != null) {
                categories.remove(category);
                return;
            }
            System.out.println("Category is not found");
        }
        System.out.println("You have no rights to delete a category.");
    }
}
