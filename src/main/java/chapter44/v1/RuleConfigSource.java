package chapter44.v1;

import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import chapter44.dependence.config_parser.rule.YamlRuleConfigParser;
import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.model.RuleConfig;

/**
 * <p>示例代码</p>
 *
 * 一般情况下，工厂模式分为三种更加细分的类型：
 *      简单工厂、
 *      工厂方法 和
 *      抽象工厂。
 *
 * 不过，在 GoF 的《设计模式》一书中，它将 简单工厂模式 看作是 工厂方法模式 的一种特例，所以，工厂模式 只被分成了
 *      工厂方法 和
 *      抽象工厂 两类。
 *
 *
 * 实际上，前面一种分类方法更加常见，所以，在今天的讲解中，我们沿用第一种分类方法。
 *
 *
 *
 *
 * 待优化代码
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */

public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new PropertiesRuleConfigParser();
        } else {
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
}
