package design_patterns.chapter72.demo2;

import design_patterns.chapter72.demo2.expression.Expression;
import design_patterns.chapter72.demo2.expression.concrete.OrExpression;

import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class AlertRuleInterpreter {

    private Expression expression;

    // key1 > 100 && key2 < 30 || key3 < 100 || key4 == 88
    public AlertRuleInterpreter(String ruleExpression) {

        this.expression = new OrExpression(ruleExpression);

    }

    //<String, Long> apiStat = new HashMap<>();
    //apiStat.put("key1", 103);
    //apiStat.put("key2", 987);
    public boolean interpret(Map<String, Long> stats) {

        return expression.interpret(stats);

    }


}


