package interfaces;

import java.util.UUID;

import models.User;

public interface CategoryInterface<T> extends LibraryInterface<T> {
    public void create(User user, String name);

    public void edit(User user, String name, UUID id);
}
