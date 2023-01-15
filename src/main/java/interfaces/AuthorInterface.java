package interfaces;

import java.util.UUID;

import models.User;

public  interface AuthorInterface<T> extends LibraryInterface<T> {
    public void create(User user, String name);

    public void edit(User user, UUID id, String name);
}
