package chapter44.v7.factory;

import chapter44.v7.factory_method.*;

import java.util.HashMap;
import java.util.Map;

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
