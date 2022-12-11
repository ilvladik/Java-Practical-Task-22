package calculator;


import java.util.EmptyStackException;
import java.util.Stack;

public class RPN {


    public static String toPostFix(String input, ArithmeticCalculator model) {
        Stack<Character> opStack = new Stack<>();
        StringBuilder postfixExr = new StringBuilder();

        for (int pos = 0; pos < input.length(); pos++) {
            char c = input.charAt(pos);

            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                for (; pos < input.length(); pos++) {
                    char num = input.charAt(pos);
                    if (Character.isDigit(num) || num == '.') {
                        number.append(num);
                    } else {
                        pos--;
                        break;
                    }
                }
                postfixExr.append(number).append(" ");
            } else if (c == '(') {
                opStack.push(c);
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    postfixExr.append(opStack.pop());
                }
                opStack.pop();
            } else if (model.getPriority(c) > 0) {

                if (c == '-' && (pos == 0 || (pos > 1 && model.getPriority(input.charAt(pos-1)) > 0))) {
                    c = '~';
                }
                while (!opStack.isEmpty() && (model.getPriority(opStack.peek()) >= model.getPriority(c))) {
                    postfixExr.append(opStack.pop());
                }
                opStack.push(c);
            } else if (c != ' ') {
                throw new IncorrectExpressionException("Incorrect expression");
            }
        }
        while (!opStack.isEmpty()) {
            postfixExr.append(opStack.pop());
        }
        return postfixExr.toString();
    }

    public static double calculate(String input, ArithmeticCalculator model) throws EmptyStackException {
        Stack<Double> locals = new Stack<>();
        for (int pos = 0; pos < input.length(); pos++) {
            char c = input.charAt(pos);

            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                for (; pos < input.length(); pos++) {
                    char num = input.charAt(pos);
                    if (Character.isDigit(num) || num == '.') {
                        number.append(num);
                    } else {
                        pos--;
                        break;
                    }
                }
                locals.push(Double.parseDouble(number.toString()));
            } else if (model.getPriority(c) > 0) {
                if (c == '~') {
                    double last = !locals.isEmpty() ? locals.pop() : 0;
                    locals.push(model.execute(0, last, '-'));
                    continue;
                }
                double second;
                double first;
                if (!locals.isEmpty()) {
                    second = locals.pop();
                } else {
                    throw new PopFailedException("Pop() failed for empty stack");
                }
                if (!locals.isEmpty()) {
                    first = locals.pop();
                } else {
                    throw new PopFailedException("Pop() failed for empty stack");
                }


                locals.push(model.execute(first, second, c));
            } else if (c != ' ') {
                throw new IncorrectExpressionException("Incorrect expression");
            }
        }
        return locals.pop();
    }
}
