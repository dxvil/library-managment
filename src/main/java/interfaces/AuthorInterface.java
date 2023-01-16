package interfaces;

import java.util.UUID;

import models.User;

public  interface AuthorInterface<T> extends LibraryInterface<T> {
    public void createAuthor(User user, String name);

    public void editAuthor(User user, UUID id, String name);
}
