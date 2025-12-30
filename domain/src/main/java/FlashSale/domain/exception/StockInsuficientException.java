package FlashSale.domain.exception;

public class StockInsuficientException extends RuntimeException {
    public StockInsuficientException(String message) {
        super(message);
    }
}
