package chapter44.v5;

import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.model.RuleConfig;
import chapter44.v5.factory_method.*;
import chapter44.v5.factory_method.impl.JsonRuleConfigParserFactory;
import chapter44.v5.factory_method.impl.PropertiesRuleConfigParserFactory;
import chapter44.v5.factory_method.impl.XmlRuleConfigParserFactory;
import chapter44.v5.factory_method.impl.YamlRuleConfigParserFactory;

/**
 * <p> 工厂方法（Factory Method） </p>
 *
 * 优化方案四：工厂方法
 *
 *
 * 通过不同的工厂，创建不同的 parser类
 *
 * 本示例，基于v3版本。
 *
 * 优点：
 * 这是工厂方法模式的典型代码实现。
 * 当我们需要添加新的规则配置解析器的时候，我们只需要创建新的 parser 类和 parser factory 类，
 * 并且在 RuleConfigParserFactoryMap 类中，将新的 parser factory 对象添加到 cachedFactories 中即可。
 * 代码的改动非常少，基本上符合开闭原则。
 * 所以， 工厂方法模式 比起 简单工厂模式 更加符合 "开闭原则" 。
 *
 * 存在问题：
 * 去掉了 createParser() 方法里面的if else，却又跑到 load() 函数中了。
 * 原因是，工厂类对象的创建逻辑又耦合进了 load() 函数中，
 * 这 跟我们最初的代码版本非常相似，可见，此时，引入工厂方法非但没有解决问题，反倒让设计变得更加复杂了。
 *
 * 解决方案：
 * 为 ‘工厂类’ 再创建一个 ‘简单工厂’ 。
 * 工厂类(JsonRuleConfigParserFactory 等)的 简单工厂(RuleConfigParserFactoryMap)，简单工厂 用来创建 工厂类 对象。
 *
 * 即，工厂的工厂
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {

        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new XmlRuleConfigParserFactory();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new PropertiesRuleConfigParserFactory();
        } else {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }


        IRuleConfigParser parser = parserFactory.createParser();
        String configText = "...";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {

        //...解析文件名获取扩展名，比如rule.json，返回json

        return "json";
    }
}
