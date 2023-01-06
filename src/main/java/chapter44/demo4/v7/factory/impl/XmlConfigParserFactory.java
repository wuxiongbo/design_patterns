package chapter44.demo4.v7.factory.impl;

import chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import chapter44.dependence.config_parser.system.XmlSystemConfigParser;
import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.config_parser.ISystemConfigParser;
import chapter44.demo4.v7.factory.IConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class XmlConfigParserFactory  implements IConfigParserFactory {

    // 规则 配置
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    // 系统 配置
    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
