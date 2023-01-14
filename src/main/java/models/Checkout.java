package models;
import java.time.LocalDate;
import java.util.UUID;

public class Checkout {
    UUID id;
    LocalDate startTime;
    LocalDate endTime;
    UUID bookId;
    UUID userId;
    boolean isReturned;
    
    Checkout(
        LocalDate startTime,
        LocalDate endTime,
        UUID bookId,
        UUID userId,
        boolean isReturned
    ) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookId = bookId;
        this.userId = userId;
        this.isReturned = isReturned;

        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public void changeStatus(boolean isReturned) {
        this.isReturned = isReturned;
    }
}
