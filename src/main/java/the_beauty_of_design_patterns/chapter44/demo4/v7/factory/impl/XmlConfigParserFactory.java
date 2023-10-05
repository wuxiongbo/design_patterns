package the_beauty_of_design_patterns.chapter44.demo4.v7.factory.impl;

import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.system.XmlSystemConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.ISystemConfigParser;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.IConfigParserFactory;

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
