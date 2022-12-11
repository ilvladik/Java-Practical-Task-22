package calculator;

public class IncorrectExpressionException extends RuntimeException {

    public IncorrectExpressionException(String errorMessage) {
        super(errorMessage);
    }

    public IncorrectExpressionException(String erroeMessage, Throwable err) {
        super(erroeMessage, err);
    }
}
