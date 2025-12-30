package FlashSale.domain.exception;

public class StockInsufficientException extends RuntimeException {
    public StockInsufficientException (String message) {
        super(message);
    }
}
