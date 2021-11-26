package chapter44.v6.factory_method.impl;

import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.impl.rule.XmlRuleConfigParser;
import chapter44.v6.factory_method.IRuleConfigParserFactory;

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
