package chapter44.v7.factory;

import chapter44.v7.factory_method.impl.JsonConfigParserFactory;
import chapter44.v7.factory_method.impl.PropertiesConfigParserFactory;
import chapter44.v7.factory_method.impl.XmlConfigParserFactory;
import chapter44.v7.factory_method.impl.YamlConfigParserFactory;
import chapter44.v7.factory_method.IConfigParserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>描述类的信息</p>
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
}