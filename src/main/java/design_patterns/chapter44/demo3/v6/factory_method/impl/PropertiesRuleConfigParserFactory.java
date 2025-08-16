package design_patterns.chapter44.demo3.v6.factory_method.impl;

import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import design_patterns.chapter44.demo3.v6.factory_method.IRuleConfigParserFactory;

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
