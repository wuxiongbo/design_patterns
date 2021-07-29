package chapter44.v4;

import chapter44.dependence.impl.rule.JsonRuleConfigParser;
import chapter44.dependence.impl.rule.PropertiesRuleConfigParser;
import chapter44.dependence.impl.rule.XmlRuleConfigParser;
import chapter44.dependence.impl.rule.YamlRuleConfigParser;
import chapter44.dependence.interface1.IRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 简单工厂模式 的第二种实现方法 </p>
 *  第二种实现方法  每次都返回同一个事先创建好的对象，也就是所谓的单例对象。
 *
 *
 * 这有点类似单例模式和简单工厂模式的结合，将 parser 事先创建好，然后缓存起来。
 * 当调用 createParser() 函数的时候，我们从缓存中取出 parser 对象直接使用。
 *
 * 本 实现方式，通过map 避开了过多的 if else 分支
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */

public class RuleConfigParserFactory {
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }
}