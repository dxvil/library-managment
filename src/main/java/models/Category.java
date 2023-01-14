package models;
import java.util.UUID;

public class Category {
    public UUID id;
    public String name;

    public Category(String name) {
        this.name = name;

        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }
    public void changeCategory(String name) {
        if(name != null || name != "") {
            this.name = name;
        }
    }

    public String toString(){
        return "Category name is " + name;
     }
}
