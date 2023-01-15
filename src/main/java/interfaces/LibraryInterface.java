package interfaces;

import java.util.UUID;

import models.User;

public interface  LibraryInterface<T> {
    public void delete(User user, UUID id, String title);

    public T find(UUID id, String title);
}
