package the_beauty_of_design_patterns.chapter72.demo2.expression.concrete;

import the_beauty_of_design_patterns.chapter72.demo2.expression.Expression;

import java.util.Map;

/**
 * <p> 大于 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public class GreaterExpression implements Expression {

    private String key;
    private long value;


    // String strExpression = "key1 > 100";
    public GreaterExpression(String strExpression) {

        // 空白符  分隔
        String[] elements = strExpression.trim().split("\\s+");

        // 元素 不为 3个， 或者 第二个位置不为 大于号  则异常
        if (elements.length != 3 || !">".equals(elements[1].trim())) {
            throw new RuntimeException("Expression is invalid: " + strExpression);
        }

        this.key = elements[0].trim(); // key1

        this.value = Long.parseLong(elements[2].trim()); // 100

    }

    public GreaterExpression(String key, long value) {
        this.key = key;
        this.value = value;
    }


    //  统计数据
    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (!stats.containsKey(key)) {
            return false;
        }
        long statValue = stats.get(key);
        return statValue > value;
    }

}
