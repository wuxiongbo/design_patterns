package chapter44.v7;

import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.model.SystemConfig;
import chapter44.dependence.config_parser.ISystemConfigParser;
import chapter44.v7.factory.ConfigParserFactoryMap;
import chapter44.v7.factory_method.IConfigParserFactory;

/**
 * <p> 工厂方法（Factory Method） 的改进 </p>
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
public class SystemConfigSource {

    public SystemConfig load(String systemConfigFilePath) throws InvalidRuleConfigException {
        // 获取系统配置文件扩展名
        String systemConfigFileExtension = getFileExtension(systemConfigFilePath);


        IConfigParserFactory parserFactory = ConfigParserFactoryMap.getParserFactory(systemConfigFileExtension);


        if (parserFactory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + systemConfigFilePath);
        }
        ISystemConfigParser parser = parserFactory.createSystemParser();

        //从 systemConfigFilePath 文件中读取配置文本到configText中
        String configText = "content...";

        // 文本内容 解析 为配置对象
        SystemConfig systemConfig = parser.parse(configText);
        return systemConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json。application.properties,返回properties
        return "json";
    }
}
