package chapter44.v7.factory_method;

import chapter44.dependence.impl.rule.XmlRuleConfigParser;
import chapter44.dependence.impl.system.XmlSystemConfigParser;
import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.interface1.ISystemConfigParser;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class XmlConfigParserFactory  implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
