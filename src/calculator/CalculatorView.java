package calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {

    JTextField expressionField = new JTextField();
    final ArithmeticCalculator model;
    final CalculatorController controller;

    CalculatorView(ArithmeticCalculator model, CalculatorController controller) {
        super("Calculator");

        expressionField.setHorizontalAlignment(JTextField.RIGHT);
        this.model = model;
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(model.getRows(), model.getColumns(), 5, 5));

        for (int row = 0; row < model.getRows(); row++) {
            for (int column = 0; column < model.getColumns(); column++) {
                JButton button = new JButton(model.getButtonName(row, column));
                button.addActionListener(e -> controller.onClick(button.getText()));
                buttons.add(button);
            }
        }

        setLayout(new BorderLayout());
        add(expressionField, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        setSize(400, 400);
    }

    public String getExpression() {
        return expressionField.getText();
    }

    public void setExpression(String expression) {
        this.expressionField.setText(expression);
    }
}
