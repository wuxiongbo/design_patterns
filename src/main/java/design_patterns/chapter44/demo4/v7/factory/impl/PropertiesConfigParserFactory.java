package design_patterns.chapter44.demo4.v7.factory.impl;

import design_patterns.chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.system.PropertiesSystemConfigParser;
import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.ISystemConfigParser;
import design_patterns.chapter44.demo4.v7.factory.IConfigParserFactory;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class PropertiesConfigParserFactory  implements IConfigParserFactory {

    // 规则 配置
    @Override
    public IRuleConfigParser createRuleParser() {
        return new PropertiesRuleConfigParser();
    }

    // 系统 配置
    @Override
    public ISystemConfigParser createSystemParser() {
        return new PropertiesSystemConfigParser();
    }
}
