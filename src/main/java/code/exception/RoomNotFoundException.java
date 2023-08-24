package code.exception;


public class RoomNotFoundException extends Exception{
    private long room_id;
    public RoomNotFoundException(long room_id) {
        super(String.format("Room is not found with id : '%s'", room_id));
    }

}
