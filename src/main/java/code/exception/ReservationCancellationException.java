package code.exception;
public class ReservationCancellationException extends Exception{
    private long book_id;
    public ReservationCancellationException(Long reservation_id) {
        super(String.format("Failed to cancel reservation with  id : '%s'", reservation_id));
    }
}
