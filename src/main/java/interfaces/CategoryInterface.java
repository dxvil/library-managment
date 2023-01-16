package interfaces;

import java.util.UUID;

import models.User;

public interface CategoryInterface<T> extends LibraryInterface<T> {
    public void createCategory(User user, String name);

    public void editCategory(User user, String name, UUID id);
}
