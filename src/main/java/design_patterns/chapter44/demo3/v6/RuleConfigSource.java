package design_patterns.chapter44.demo3.v6;

import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import design_patterns.chapter44.dependence.exception.InvalidRuleConfigException;
import design_patterns.chapter44.dependence.model.RuleConfig;
import design_patterns.chapter44.demo3.v6.factory_produce.RuleConfigParserFactoryMap;
import design_patterns.chapter44.demo3.v6.factory_method.IRuleConfigParserFactory;

/**
 * <p> 工厂方法（Factory Method） 的改进 </p>
 *
 *
 * 当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，做各种初始化操作的时候，我们推荐使用 工厂方法模式 ，
 * 将复杂的创建逻辑拆分到多个工厂类中，让每个工厂类都不至于过于复杂。
 * 这种情况，如果使用 “简单工厂模式” ，将所有的创建逻辑都放到一个工厂类中，将会导致这个工厂类变得很复杂。
 * 此时，如果添加新的 规则配置解析器， 对 工厂类的改动也会比较大
 *
 *
 * 优化方案五：工厂方法 + 工厂的工厂(v4改进版)
 *
 * 当我们需要添加新的  规则配置解析器  的时候，我们只需要
 *    1.创建 新的 "parser"类  和 "parser factory"类，
 *    2.并修改 RuleConfigParserFactoryMap 类，将新的 parser factory 对象添加到 cachedFactories 中即可。
 *      代码的改动非常少，基本上符合开闭原则。
 *
 *
 * 简单工厂模式 vs 工厂方法模式:
 *
 * 实际上，对于 "规则配置文件解析" 这个应用场景来说，工厂模式需要额外创建诸多 Factory 类，也会增加代码的复杂性，
 * 而且，每个 Factory 类只是做简单的 new 操作，功能非常单薄（只有一行代码），也没必要设计成独立的类，
 * 所以，在这个应用场景下，简单工厂模式简单好用，比工厂方法模式更加合适。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        //将  不同“工厂类的创建”逻辑， 进一步抽象到 一个独立的类。  工厂类的工厂类
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);


        check(ruleConfigFilePath,parserFactory);


        IRuleConfigParser parser = parserFactory.createParser();



        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }


    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }


    private void check(String ruleConfigFilePath, IRuleConfigParserFactory parserFactory) throws InvalidRuleConfigException {
        if (parserFactory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
    }

}
