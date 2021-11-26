package chapter44.v7.factory_method.impl;

import chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import chapter44.dependence.config_parser.system.PropertiesSystemConfigParser;
import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.config_parser.ISystemConfigParser;
import chapter44.v7.factory_method.IConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class PropertiesConfigParserFactory  implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new PropertiesRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new PropertiesSystemConfigParser();
    }
}
