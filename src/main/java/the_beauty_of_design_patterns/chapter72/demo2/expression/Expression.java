package the_beauty_of_design_patterns.chapter72.demo2.expression;

import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/19
 * </pre>
 */
public interface Expression {

    // 解释
    boolean interpret(Map<String, Long> stats);

}
