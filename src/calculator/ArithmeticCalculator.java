package calculator;

public class ArithmeticCalculator {

    private final String[][] buttons;
    private final int rows;
    private final int columns;

    public ArithmeticCalculator(String[][] buttons) {
        if (buttons != null) {
            this.buttons = buttons;
            this.rows = buttons.length;
            this.columns = buttons[0].length;
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    public int getPriority(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            case '~' -> 4;
            default -> 0;
        };
    }

    public double execute(double a, double b, char c) {
        if (c == '/' && b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return switch (c) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '^' -> Math.pow(a, b);
            default -> 0;
        };
    }

    public String getButtonName(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return buttons[row][column];
        } else {
            throw new ArrayIndexOutOfBoundsException("Array index out of bounds");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
