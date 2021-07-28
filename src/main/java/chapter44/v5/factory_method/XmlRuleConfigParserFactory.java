package chapter44.v5.factory_method;

import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.impl.rule.XmlRuleConfigParser;
import chapter44.v5.IRuleConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class XmlRuleConfigParserFactory  implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
