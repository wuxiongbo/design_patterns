package chapter44.v7.factory_method;

import chapter44.dependence.impl.rule.JsonRuleConfigParser;
import chapter44.dependence.impl.system.JsonSystemConfigParser;
import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.interface1.ISystemConfigParser;
import chapter44.v7.IConfigParserFactory;

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
