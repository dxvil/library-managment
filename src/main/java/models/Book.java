package models;
import java.util.UUID;

public class Book {
    public UUID id;
    public String title;
    String description;
    Category category;
    Author author;

    public Book(String title, String description, Category category, Author author){
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        setUuid();
    }

    public UUID getUuid() {
        return id;
    }

    private void setUuid() {
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category newCategory) {
        this.category = newCategory;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author newAuthor) {
        this.author = newAuthor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.title = newDescription;
    }

    public void updateBook(UUID id, String title, String description, Category category, Author author) {
        if(id != null) {
            if(title != null || title != "") {
                setTitle(title);
            }
            if(description != null || title != "") {
                setDescription(description);
            }
            if(category != null) {
                setCategory(category);
            }
            
            if(author != null) {
                setAuthor(author);
            }
        }
    }
}
