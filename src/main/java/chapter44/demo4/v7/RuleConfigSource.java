package chapter44.demo4.v7;

import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.model.RuleConfig;
import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.demo4.v7.factory_produce.ConfigParserFactoryMap;
import chapter44.demo4.v7.factory.IConfigParserFactory;

/**
 * <p> 抽象工厂 </p>
 *
 * 规则配置
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigSource {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);


        // 将  不同“工厂类的创建”逻辑， 进一步抽象到 一个独立的类。
        IConfigParserFactory parserFactory = ConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);


        check(ruleConfigFilePath,parserFactory);



        // **函数隔离** 。 什么场景，调用什么函数
        IRuleConfigParser parser = parserFactory.createRuleParser();
//        ISystemConfigParser parser = parserFactory.createSystemParser();


        String configText = "content...";//从 ruleConfigFilePath 文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);// 文本内容 解析 为配置对象
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json。application.properties,返回properties
        return "json";
    }

    private void check(String systemConfigFilePath,IConfigParserFactory parserFactory) throws InvalidRuleConfigException {
        if (parserFactory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + systemConfigFilePath);
        }
    }

}
