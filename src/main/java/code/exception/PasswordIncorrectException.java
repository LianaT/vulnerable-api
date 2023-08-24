package code.exception;

public class PasswordIncorrectException extends Exception{
    private long id;
    public PasswordIncorrectException() {
        super(String.format("The password cannot match the member ID."));
    }
}
