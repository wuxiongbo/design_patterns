package chapter44.v6;

import chapter44.v6.factory_method.*;
import chapter44.v6.factory_method.impl.JsonRuleConfigParserFactory;
import chapter44.v6.factory_method.impl.PropertiesRuleConfigParserFactory;
import chapter44.v6.factory_method.impl.XmlRuleConfigParserFactory;
import chapter44.v6.factory_method.impl.YamlRuleConfigParserFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述类的信息</p>
 * 因为工厂类只包含方法，不包含成员变量，完全可以复用，
 *
 * 不需要每次都创建新的工厂类对象(缓存)，所以，简单工厂模式的第二种实现思路（v4  将类提前创建好，并缓存）更加合适。
 *
 * 简单工厂模式 vs 工厂方法模式:
 * 实际上，对于 "规则配置文件解析" 这个应用场景来说，工厂模式需要额外创建诸多 Factory 类，也会增加代码的复杂性，
 * 而且，每个 Factory 类只是做简单的 new 操作，功能非常单薄（只有一行代码），也没必要设计成独立的类，
 * 所以，在这个应用场景下，简单工厂模式简单好用，比工厂方法模式更加合适。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigParserFactoryMap { //工厂的工厂

    private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        IRuleConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase());
        return parserFactory;
    }
}
