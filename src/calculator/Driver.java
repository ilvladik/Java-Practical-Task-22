package calculator;

public class Driver {

    public static void main(String[] args) {
        String[][] buttonsPosition = getButtonsPosition();
        ArithmeticCalculator model = new ArithmeticCalculator(buttonsPosition);
        CalculatorController controller = new CalculatorController();
        CalculatorView view = new CalculatorView(model, controller);
        controller.setModel(model);
        controller.setView(view);
        controller.start();
    }

    public static String[][] getButtonsPosition() {
        return new String[][] {
                {"1", "2", "3", "/"},
                {"4", "5", "6", "*"},
                {"7", "8", "9", "-"},
                {".", "0", "=", "+"}
        };
    }
}
