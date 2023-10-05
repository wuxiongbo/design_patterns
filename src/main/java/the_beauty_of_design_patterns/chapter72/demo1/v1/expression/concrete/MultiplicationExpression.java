package the_beauty_of_design_patterns.chapter72.demo1.v1.expression.concrete;

import the_beauty_of_design_patterns.chapter72.demo1.v1.expression.Expression;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public class MultiplicationExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public MultiplicationExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public long interpret() {
        return exp1.interpret() * exp2.interpret();
    }
}
