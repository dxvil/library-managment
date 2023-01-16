package models;
import java.util.UUID;

public class Category {
    public UUID id;
    public String name;

    public Category(String name) {
        this.name = name;
        setUuid();
    }

    public UUID getUuid() {
        return id;
    }

    private void setUuid() {
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }
  
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void changeCategory(String name) {
        if(name != null || name != "") {
            setName(name);
        }
    }

    public String toString(){
        return "Category name is " + name;
     }
}
