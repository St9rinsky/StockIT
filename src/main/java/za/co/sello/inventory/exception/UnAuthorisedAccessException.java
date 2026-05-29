package za.co.sello.inventory.exception;

public class UnAuthorisedAccessException extends RuntimeException {
    public UnAuthorisedAccessException(String message) {super(message);
    }
}