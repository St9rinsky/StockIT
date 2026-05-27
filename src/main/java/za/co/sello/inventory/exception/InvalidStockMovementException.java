package za.co.sello.inventory.exception;

public class InvalidStockMovementException extends RuntimeException {
    public InvalidStockMovementException(String message) {
        super(message);
    }
}
