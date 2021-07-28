package chapter44.v6.factory_method;

import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.impl.rule.JsonRuleConfigParser;
import chapter44.v6.IRuleConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class JsonRuleConfigParserFactory  implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
