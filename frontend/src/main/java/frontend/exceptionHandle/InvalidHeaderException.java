package frontend.exceptionHandle;

public class InvalidHeaderException extends RuntimeException {

    public InvalidHeaderException(String message){
        super(message);
    }
}