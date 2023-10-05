package the_beauty_of_design_patterns.chapter44.demo4.v7.factory_produce;

import the_beauty_of_design_patterns.chapter44.demo4.v7.RuleConfigSource;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.impl.JsonConfigParserFactory;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.impl.PropertiesConfigParserFactory;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.impl.XmlConfigParserFactory;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.impl.YamlConfigParserFactory;
import the_beauty_of_design_patterns.chapter44.demo4.v7.factory.IConfigParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>工厂模式</p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class ConfigParserFactoryMap { //工厂的工厂

    private static final Map<String, IConfigParserFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("json", new JsonConfigParserFactory());
        cachedFactories.put("xml", new XmlConfigParserFactory());
        cachedFactories.put("yaml", new YamlConfigParserFactory());
        cachedFactories.put("properties", new PropertiesConfigParserFactory());
    }

    public static IConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        IConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase());
        return parserFactory;
    }
}








/**
 * <p>工厂模式 在函数式编程下的新思路 </p>
 *
 * 类似v3的改版。
 *
 * 用java.util.functional实现现代函数式编程范式的设计模式
 * JsonConfigParserFactory、XmlConfigParserFactory等,可以看作工厂,也可以看作获取一种parse策略。
 *
 * 可以有一个FunctionFactory内部维护一组Function<String,String>函数，再有一个Map容器 mapping type和Function的关系。
 * 这样是简化了类的数量，如果业务简单没必要整太多类，function铺在一个factory里可读性不会有什么问题。
 * 如果是没有返回值的操作，也可以用Consumer函数。
 *
 * 缺点：
 * 这样创建的类就不是单例了
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
class ConfigParserFactoryMapJDK8 { //工厂的工厂

    private static final Map<String, Supplier<IConfigParserFactory>> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("json", JsonConfigParserFactory::new);
        cachedFactories.put("xml",  XmlConfigParserFactory::new);
        cachedFactories.put("yaml", YamlConfigParserFactory::new);
        cachedFactories.put("properties", PropertiesConfigParserFactory::new);
    }

    public static IConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        Supplier<IConfigParserFactory> parserFactory = cachedFactories.get(type.toLowerCase());
        if(parserFactory!=null){
            // 用 Supplier 进行函数式编程，实现 懒加载。 使用的时候创建对象。 不过是多例
            return parserFactory.get();
        }

        throw new IllegalArgumentException("No such shape " + type);
    }


    public static void main(String[] args){
        RuleConfigSource ruleConfigSource = new RuleConfigSource();
        String ruleConfigFileExtension = getFileExtension("89899");

        IConfigParserFactory parserFactory = ConfigParserFactoryMapJDK8.getParserFactory(ruleConfigFileExtension);
        IConfigParserFactory parserFactory1 = ConfigParserFactoryMapJDK8.getParserFactory(ruleConfigFileExtension);

        // 每次拿的都是不同的实例。
        System.out.println(parserFactory.hashCode());
        System.out.println(parserFactory1.hashCode());

    }
    private static String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json。application.properties,返回properties
        return "json";
    }
}