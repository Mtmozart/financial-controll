package inancial_control.api.domain.transaction;

public class ValidationException extends RuntimeException {
    public ValidationException(String message){
        super(message);
    }
}
