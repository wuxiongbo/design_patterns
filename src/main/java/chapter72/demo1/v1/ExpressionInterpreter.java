package chapter72.demo1.v1;

import chapter72.demo1.v1.expression.AdditionExpression;
import chapter72.demo1.v1.expression.DivisionExpression;
import chapter72.demo1.v1.expression.Expression;
import chapter72.demo1.v1.expression.MultiplicationExpression;
import chapter72.demo1.v1.expression.NumberExpression;
import chapter72.demo1.v1.expression.SubstractionExpression;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class ExpressionInterpreter {

    private Deque<Expression> numbers = new LinkedList<>();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length+1)/2; ++i) {
            numbers.addLast(new NumberExpression(elements[i]));
        }

        for (int i = (length+1)/2; i < length; ++i) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator) || "-".equals(operator)
                    || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            Expression exp1 = numbers.pollFirst();
            Expression exp2 = numbers.pollFirst();
            Expression combinedExp = null;


            if ("+".equals(operator)) {
                combinedExp = new AdditionExpression(exp1, exp2);
            } else if ("-".equals(operator)) {
                combinedExp = new SubstractionExpression(exp1, exp2);
            } else if ("*".equals(operator)) {
                combinedExp = new MultiplicationExpression(exp1, exp2);
            } else if ("/".equals(operator)) {
                combinedExp = new DivisionExpression(exp1, exp2);
            }


            long result = combinedExp.interpret();
            numbers.addFirst(new NumberExpression(result));
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop().interpret();
    }
}
