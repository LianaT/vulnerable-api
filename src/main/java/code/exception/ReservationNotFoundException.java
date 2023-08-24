package code.exception;


public class ReservationNotFoundException extends Exception{
    private long author_id;
    public ReservationNotFoundException(long reservation_id) {
        super(String.format("Reservation is not found with id : '%s'", reservation_id));
    }

}
