import java.util.ArrayList;
import java.util.UUID;

public class CategoryController {
    ArrayList<Category> categories = new ArrayList<Category>();
    
    public Category findCategory(UUID id, String title) {
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
            Category category = findCategory(id, "");
            if(category != null) {
                category.changeCategory(name);
                return;
            }
            System.out.println("Category is not found");
        }
        System.out.println("You have no rights to edit a category.");
    }

    public void deleteCategory(User user, UUID id) {
        if(user != null &&  user.role == Role.ADMIN) {
            Category category = findCategory(id, "");
            if(category != null) {
                categories.remove(category);
                return;
            }
            System.out.println("Category is not found");
        }
        System.out.println("You have no rights to delete a category.");
    }
}
