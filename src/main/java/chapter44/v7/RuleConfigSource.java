package chapter44.v7;

import chapter44.dependence.InvalidRuleConfigException;
import chapter44.dependence.RuleConfig;
import chapter44.dependence.interface1.IRuleConfigParser;

/**
 * <p> 工厂方法（Factory Method） 的改进 </p>
 *
 * 简单工厂模式 vs 工厂方法模式:
 * 实际上，对于 "规则配置文件解析" 这个应用场景来说，工厂模式需要额外创建诸多 Factory 类，也会增加代码的复杂性，
 * 而且，每个 Factory 类只是做简单的 new 操作，功能非常单薄（只有一行代码），也没必要设计成独立的类，
 * 所以，在这个应用场景下，‘简单工厂模式’ 简单好用，比 ‘工厂方法模式’ 更加合适。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IConfigParserFactory parserFactory = ConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        IRuleConfigParser parser = parserFactory.createRuleParser();

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }

    public static void main(String[] args){
        RuleConfigSource ruleConfigSource = new RuleConfigSource();
        String ruleConfigFileExtension = ruleConfigSource.getFileExtension("89899");

        IConfigParserFactory parserFactory = ConfigParserFactoryMapJDK8.getParserFactory(ruleConfigFileExtension);
        IConfigParserFactory parserFactory1 = ConfigParserFactoryMapJDK8.getParserFactory(ruleConfigFileExtension);

        System.out.println(parserFactory.hashCode());
        System.out.println(parserFactory1.hashCode());
    }

}
