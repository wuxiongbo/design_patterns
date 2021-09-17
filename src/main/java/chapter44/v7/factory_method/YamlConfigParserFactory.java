package chapter44.v7.factory_method;

import chapter44.dependence.impl.rule.YamlRuleConfigParser;
import chapter44.dependence.impl.system.YamlSystemConfigParser;
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
public class YamlConfigParserFactory  implements IConfigParserFactory {

    @Override
    public IRuleConfigParser createRuleParser() {
        return new YamlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new YamlSystemConfigParser();
    }
}
