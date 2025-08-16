package design_patterns.chapter72.demo1.v1;

import design_patterns.chapter72.demo1.v1.expression.Expression;
import design_patterns.chapter72.demo1.v1.expression.concrete.AdditionExpression;
import design_patterns.chapter72.demo1.v1.expression.concrete.DivisionExpression;
import design_patterns.chapter72.demo1.v1.expression.concrete.MultiplicationExpression;
import design_patterns.chapter72.demo1.v1.expression.concrete.NumberExpression;
import design_patterns.chapter72.demo1.v1.expression.concrete.SubstractionExpression;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>解释器模式</p>
 *
 *
 * 解释器模式的代码实现比较灵活，没有固定的模板。
 *
 * 我们前面说过，应用设计模式主要是应对代码的复杂性，解释器模式也不例外。
 * 它的代码实现的核心思想，就是 “将语法解析的工作拆分到各个小类中，以此来避免大而全的解析类” 。
 * 一般的做法是，将 语法规则 拆分成一些  小的独立的单元，然后对每个单元进行解析，最终 合并为对整个语法规则的解析。
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class ExpressionInterpreter {

    private Deque<Expression> numbers = new LinkedList<>();

    // “ 8 3 2 4 - + * ”
    public long interpret(String expression) {

        // 空格分隔
        String[] elements = expression.split(" ");


        int length = elements.length;


        // 数字 处理
        for (int i = 0; i < (length+1)/2; ++i) {
            numbers.addLast(new NumberExpression(elements[i]));
        }

        // 符号 处理
        for (int i = (length+1)/2; i < length; ++i) {

            String operator = elements[i];

            // 校验运算符
            boolean isValid = "+".equals(operator) || "-".equals(operator)
                    || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }


            Expression exp1 = numbers.pollFirst();
            Expression exp2 = numbers.pollFirst();

            // 解释器
            Expression combinedExp = switch (operator) {
                case "+" -> new AdditionExpression(exp1, exp2);
                case "-" -> new SubstractionExpression(exp1, exp2);
                case "*" -> new MultiplicationExpression(exp1, exp2);
                case "/" -> new DivisionExpression(exp1, exp2);
                default -> null;
            };


            // 解释运算
            long result = combinedExp.interpret();

            // 入队
            numbers.addFirst(new NumberExpression(result));
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        // 得到最终结果，出栈
        return numbers.pop().interpret();
    }
}
