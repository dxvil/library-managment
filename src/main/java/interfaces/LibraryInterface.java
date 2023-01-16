package interfaces;

import java.util.UUID;

import models.User;

public interface  LibraryInterface<T> {
    public void deleteOne(User user, UUID id, String title);

    public T findOne(UUID id, String title);
}
