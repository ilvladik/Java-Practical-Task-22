package calculator;

import java.util.EmptyStackException;

public class CalculatorController {

    private CalculatorView view;
    private ArithmeticCalculator model;

    public CalculatorController() {}

    public void setModel(ArithmeticCalculator model) {
        this.model = model;
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }

    public void start() {
        view.setVisible(true);
    }

    public void onClick(String buttonName) {
        String expression = view.getExpression();
        if (!buttonName.equals("=")) {
            view.setExpression(expression + buttonName);
        } else if (!expression.isEmpty()) {
            try {
                double result = calculate(view.getExpression());
                view.setExpression(Double.toString(result));
            } catch (ArithmeticException arithmetic) {
                view.setExpression(arithmetic.getMessage());
            } catch (IncorrectExpressionException expressionException) {
                view.setExpression(expressionException.getMessage());
            } catch (NumberFormatException numberException) {
                view.setExpression(numberException.getMessage());
            } catch (PopFailedException stackException) {
                view.setExpression(stackException.getMessage());
            }
        }
    }

    public double calculate(String expression) {
        String postFixEntry = RPN.toPostFix(expression, model);
        double result = RPN.calculate(postFixEntry, model);
        return result;
    }
}
