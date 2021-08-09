package chapter44.v2;

import chapter44.dependence.*;
import chapter44.dependence.impl.rule.JsonRuleConfigParser;
import chapter44.dependence.impl.rule.PropertiesRuleConfigParser;
import chapter44.dependence.impl.rule.XmlRuleConfigParser;
import chapter44.dependence.impl.rule.YamlRuleConfigParser;
import chapter44.dependence.interface1.IRuleConfigParser;

/**
 * <p>工厂模式</p>
 *
 * 基于“规范和重构”，为了让代码逻辑更加清晰，可读性更好，我们要善于将功能独立的代码块封装成函数。
 *
 * parser 创建的部分逻辑剥离出来，抽象成 createParser() 函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */

public class RuleConfigSource {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        // 解析 逻辑，抽象出来
        IRuleConfigParser parser = createParser(ruleConfigFileExtension);

        if (parser == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }







    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }

    private IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }

}
