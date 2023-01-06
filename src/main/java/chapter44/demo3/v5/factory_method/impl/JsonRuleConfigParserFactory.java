package chapter44.demo3.v5.factory_method.impl;

import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import chapter44.demo3.v5.factory_method.IRuleConfigParserFactory;

/**
 * <p>Json解析器工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class JsonRuleConfigParserFactory  implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        // ...依赖对象的复杂创建逻辑
        return new JsonRuleConfigParser(/*省略复杂的依赖注入*/);
    }


}
