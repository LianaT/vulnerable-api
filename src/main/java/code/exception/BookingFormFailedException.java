package code.exception;


public class BookingFormFailedException extends Exception{

    public BookingFormFailedException( ){
        super(String.format("Booking form failed to load."));
    }

}
