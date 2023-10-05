package the_beauty_of_design_patterns.chapter72.demo2.expression.concrete;

import the_beauty_of_design_patterns.chapter72.demo2.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 责任链2 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class OrExpression  implements Expression {

    private List<Expression> expressions = new ArrayList<>();

    //  入口：
    //  key1 > 100 && key2 < 30 || key3 < 100 || key4 == 88
    public OrExpression(String strOrExpression) {

        String[] andExpressions = strOrExpression.split("\\|\\|");

        //  key1 > 100 && key2 < 30
        //  key3 < 100
        //  key4 == 88
        for (String andExpr : andExpressions) {

            expressions.add(new AndExpression(andExpr));

        }

    }

    public OrExpression(List<Expression> expressions) {
        this.expressions.addAll(expressions);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {

        for (Expression expr : expressions) {

            if (expr.interpret(stats)) {
                return true;
            }

        }

        return false;
    }
}
