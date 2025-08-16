package design_patterns.chapter72.demo1.v0;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * <p>解释器模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */

public class ExpressionInterpreter {
    private Deque<Long> numbers = new LinkedList<>();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length+1)/2; ++i) {
            numbers.addLast(Long.parseLong(elements[i]));
        }

        for (int i = (length+1)/2; i < length; ++i) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator) || "-".equals(operator)
                    || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            Long number1 = numbers.pollFirst();
            Long number2 = numbers.pollFirst();
            if (Objects.isNull(number1)|| Objects.isNull(number2)){
                continue;
            }

            long result = switch (operator) {
                case "+" -> number1 + number2;
                case "-" -> number1 - number2;
                case "*" -> number1 * number2;
                case "/" -> number1 / number2;
                default -> 0;
            };
            numbers.addFirst(result);
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop();
    }
}
