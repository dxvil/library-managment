package models;
import java.util.UUID;

public class Author {
    public UUID id;
    public String name;

    public Author(String name) {
        this.name = name;
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public void editAuthor(String name) {
        if(name != "") {
            this.name = name;
        }
    }

    public String toString(){
        return "Author name is " + name;
     }
}
