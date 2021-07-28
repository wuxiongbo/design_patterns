package chapter44.dependence.interface1;

import chapter44.dependence.RuleConfig;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface IRuleConfigParser {
    public RuleConfig parse(String configText);
}
