package the_beauty_of_design_patterns.chapter44.demo2.v4.factory;

import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.rule.YamlRuleConfigParser;
import the_beauty_of_design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 简单工厂模式 的第二种实现方法 </p>
 *
 * 第二种实现方法：  每次都返回同一个事先创建好的对象，也就是所谓的单例对象。
 *
 *
 * 这有点类似  “单例模式” 和  “简单工厂模式”  的结合，将 parser 事先创建好，然后缓存起来。
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

    private static final Map<String, IRuleConfigParser> CACHED_PARSERS = new HashMap<>();

    // 工厂模式改进版
    // 在静态方法中实例化
    static {
        CACHED_PARSERS.put("json", new JsonRuleConfigParser());
        CACHED_PARSERS.put("xml", new XmlRuleConfigParser());
        CACHED_PARSERS.put("yaml", new YamlRuleConfigParser());
        CACHED_PARSERS.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;  //返回 null 还是 IllegalArgumentException，全凭你自己说了算
        }
        return CACHED_PARSERS.get(configFormat.toLowerCase());
    }
}