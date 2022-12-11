package calculator;

public class PopFailedException extends RuntimeException {

    public PopFailedException(String errorMessage) {
        super(errorMessage);
    }

    public PopFailedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
