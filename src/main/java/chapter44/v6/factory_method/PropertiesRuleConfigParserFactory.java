package chapter44.v6.factory_method;

import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.impl.rule.PropertiesRuleConfigParser;
import chapter44.v6.IRuleConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class PropertiesRuleConfigParserFactory  implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}
