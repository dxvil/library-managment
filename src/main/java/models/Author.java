package models;
import java.util.UUID;

public class Author {
    public UUID id;
    public String name;

    public Author(String name) {
        this.name = name;
        setUuid();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public UUID getUuid() {
        return id;
    }

    private void setUuid() {
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public void editAuthor(String name) {
        if(name != "") {
            setName(name);
        }
    }

    public String toString(){
        return "Author name is " + name;
     }
}
