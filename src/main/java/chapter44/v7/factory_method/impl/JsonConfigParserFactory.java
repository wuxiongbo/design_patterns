package chapter44.v7.factory_method.impl;

import chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import chapter44.dependence.config_parser.system.JsonSystemConfigParser;
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
public class JsonConfigParserFactory  implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}
