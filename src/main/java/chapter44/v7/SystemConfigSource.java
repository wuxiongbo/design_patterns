package chapter44.v7;

import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.model.SystemConfig;
import chapter44.dependence.config_parser.ISystemConfigParser;
import chapter44.v7.factory.ConfigParserFactoryMap;
import chapter44.v7.factory_method.IConfigParserFactory;

/**
 * <p> 抽象工厂 </p>
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

        // 函数隔离
        ISystemConfigParser parser = parserFactory.createSystemParser();

        String configText = "content..."; //从 systemConfigFilePath 文件中读取配置文本到configText中
        SystemConfig systemConfig = parser.parse(configText); // 文本内容 解析 为配置对象
        return systemConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json。application.properties,返回properties
        return "json";
    }
}
