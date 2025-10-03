package domain.exceptions;

public class CardValidationExeception extends RuntimeException {
    public CardValidationExeception(String message) {
        super(message);
    }
}
