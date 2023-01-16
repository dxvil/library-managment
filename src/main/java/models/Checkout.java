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
        setUuid();
    }

    public UUID getUuid() {
        return id;
    }

    private void setUuid() {
        Identifier identifier = new Identifier();
        this.id = identifier.generateUuid();
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate newStartTime) {
        this.startTime = newStartTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate newEndTime) {
        this.endTime = newEndTime;
    }

    public void setBookUuid(UUID newBookUuid) {
        this.bookId = newBookUuid;
    }

    public UUID getBookUuid() {
        return bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID newUserUuid) {
        this.userId = newUserUuid;
    }

    public boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public void changeCheckoutStatus(boolean isReturned) {
        setIsReturned(isReturned);
    }
}
