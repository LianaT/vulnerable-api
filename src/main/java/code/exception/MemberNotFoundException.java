package code.exception;

public class MemberNotFoundException extends Exception{
    private long id;
    public MemberNotFoundException(long id) {
        super(String.format("Membership is not found with id : '%s'", id));
    }
}
