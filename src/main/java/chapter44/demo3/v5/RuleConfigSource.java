package chapter44.demo3.v5;

import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.model.RuleConfig;
import chapter44.demo3.v5.factory_method.*;
import chapter44.demo3.v5.factory_method.impl.JsonRuleConfigParserFactory;
import chapter44.demo3.v5.factory_method.impl.PropertiesRuleConfigParserFactory;
import chapter44.demo3.v5.factory_method.impl.XmlRuleConfigParserFactory;
import chapter44.demo3.v5.factory_method.impl.YamlRuleConfigParserFactory;

/**
 * <p> 工厂方法（Factory Method） </p>
 *
 * 工厂方法模式的典型实现
 *
 * 优化方案四：工厂方法
 *
 * 当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，做各种初始化操作的时候，我们推荐使用 工厂方法模式 ，
 * 将复杂的创建逻辑拆分到多个工厂类中，让每个工厂类都不至于过于复杂。
 * 这种情况，如果使用 “简单工厂模式” ，将所有的创建逻辑都放到一个工厂类中，将会导致这个工厂类变得很复杂。
 * 此时，如果添加新的 规则配置解析器， 对 工厂类的改动也会比较大
 *
 *
 * 解决思路是：通过不同的工厂，创建不同的 parser类
 *
 *
 * 本示例，基于v3版本。
 *
 * 优点：
 *    这是工厂方法模式的典型代码实现。
 *    当我们需要添加新的  规则配置解析器  的时候，我们只需要创建新的 parser 类  和   parser factory 类，
 *    没有对原工厂类代码的改动，符合开闭原则。
 *    所以， 工厂方法模式 比起 简单工厂模式 更加符合 "开闭原则" 。
 *
 *
 *    这里所说的更符合“开闭原则”是针对工厂类来说的。
 *    对于简单工厂模式，由于大家共用一个工厂，只要涉及新增parse，一定需要修改原工厂类；
 *    对于工厂方法模式，每个parse对应一个自己的工厂，如果新增一个parse，相对应的要新增一个工厂类，不用修改原有工厂，
 *    所以说，“工厂方法模式” 比 “简单工厂模式” 更符合“开闭原则”。
 *
 *
 *
 * 存在问题：
 *    去掉了 createParser() 方法里面的if else，却又跑回到 load() 函数中了。
 *    原因是，工厂类对象的创建逻辑又耦合进了 load() 函数中，
 *    这 跟我们最初的代码版本非常相似，可见，此时，引入工厂方法非但没有解决问题，反倒让设计变得更加复杂了。
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


        // 将  不同“解析类的创建” 逻辑 抽象成不同的‘创建方法’，不同的创建方法 封装到 不同的独立工厂类中。 各个 工厂类 统称 工厂方法
        IRuleConfigParserFactory parserFactory = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        }
        else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new XmlRuleConfigParserFactory();
        }
        else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        }
        else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parserFactory = new PropertiesRuleConfigParserFactory();
        }
        else {
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
