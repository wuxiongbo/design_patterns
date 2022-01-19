package chapter72.demo2;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>告警规则</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class DemoTest {
    public static void main(String[] args) {

        // 告警规则
        // “>、<、==”运算符的  优先级  高于 “||、&&”运算符，
        // “&&”运算符  优先级 高于  “||”
        String rule = "key1 > 100 && key2 < 30 || key3 < 100 || key4 == 88";


        // 告警规则解释器
        // 分隔顺序
        //    ||              OrExpression
        //    &&              AndExpression
        //    > 、 < 、 ==     GreaterExpression / LessExpression / EqualExpression
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);


        // 统计数据
        Map<String, Long> stats = new HashMap<>();
        stats.put("key1", 101L);
        stats.put("key3", 121L);
        stats.put("key4", 88L);


        // 输入 统计数据  ，根据 告警规则 决定是否告警
        // 多级责任链
        boolean alert = interpreter.interpret(stats);

        System.out.println(alert);

    }
}
