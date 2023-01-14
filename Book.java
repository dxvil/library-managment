import java.util.UUID;

public class Book {
    UUID id;
    String title;
    String description;
    Category category;
    Author author;

    Book(String title, String description, Category category, Author author){
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public void updateBook(UUID id, String title, String description, Category category, Author author) {
        if(id != null) {
            if(title != null || title != "") {
                this.title = title;
            }
            if(description != null || title != "") {
                this.description = description;
            }
            if(category != null) {
                this.category = category;
            }
            
            if(author != null) {
                this.author = author;
            }
        }
    }
}
