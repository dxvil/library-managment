import java.util.UUID;

public class Book {
    UUID id;
    String title;
    String description;
    Category categoryId;
    Author author;

    Book(String title, String description, Category categoryId, Author author){
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.author = author;
        
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public void updateBook(UUID id, String title, String description, Category categoryId, Author author) {
        if(id != null) {
            if(title != null || title != "") {
                this.title = title;
            }
            if(description != null || title != "") {
                this.description = description;
            }
            if(categoryId != null) {
                this.categoryId = categoryId;
            }
            
            if(author != null) {
                this.author = author;
            }
        }
    }
}
