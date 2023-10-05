package the_beauty_of_design_patterns.chapter44.demo3.v5.factory_method.impl;

import the_beauty_of_design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.YamlRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.demo3.v5.factory_method.IRuleConfigParserFactory;

/**
 * <p>Yaml解析器工厂</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class YamlRuleConfigParserFactory  implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        // ...依赖对象的复杂创建逻辑
        return new YamlRuleConfigParser(/*省略复杂的依赖注入*/);
    }




}
