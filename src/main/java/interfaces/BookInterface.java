package interfaces;

import java.util.UUID;

import models.Author;
import models.Category;
import models.User;

public interface BookInterface<T> extends LibraryInterface<T> {
    public void editBook(User user, UUID id, String title, String description, Category categoryId, Author author);

    public T getBook(UUID id, String title);
}
