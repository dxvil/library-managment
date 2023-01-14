import java.util.UUID;

public class Category {
    UUID id;
    String name;

    Category(String name) {
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
