package design_patterns.chapter44.demo1.v1;

import design_patterns.chapter44.dependence.exception.InvalidRuleConfigException;
import design_patterns.chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.rule.YamlRuleConfigParser;
import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import design_patterns.chapter44.dependence.model.RuleConfig;

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
        // 1.解析文件扩展名
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        // 2.匹配 配置解析器
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

        // 3.从 ruleConfigFilePath 路径文件中，读取 配置文本 到configText中
        String configText = "";

        // 4.解析对应格式的配置文本
        RuleConfig ruleConfig = parser.parse(configText);

        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
