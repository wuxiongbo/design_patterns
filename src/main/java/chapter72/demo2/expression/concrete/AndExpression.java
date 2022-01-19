package chapter72.demo2.expression.concrete;

import chapter72.demo2.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 责任链1 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class AndExpression  implements Expression {

    private List<Expression> expressions = new ArrayList<>();

    //  key1 > 100 && key2 < 30
    //  key3 < 100
    //  key4 == 88
    public AndExpression(String strAndExpression) {

        String[] strExpressions = strAndExpression.split("&&");

        //  key1 > 100
        //  key2 < 30
        //  key3 < 100
        //  key4 == 88
        for (String strExpr : strExpressions) {
            if (strExpr.contains(">")) {
                expressions.add(new GreaterExpression(strExpr));
            } else if (strExpr.contains("<")) {
                expressions.add(new LessExpression(strExpr));
            } else if (strExpr.contains("==")) {
                expressions.add(new EqualExpression(strExpr));
            } else {
                throw new RuntimeException("Expression is invalid: " + strAndExpression);
            }
        }
    }

    public AndExpression(List<Expression> expressions) {
        this.expressions.addAll(expressions);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {

        for (Expression expr : expressions) {
            if (!expr.interpret(stats)) {
                return false;
            }
        }

        return true;
    }
}
