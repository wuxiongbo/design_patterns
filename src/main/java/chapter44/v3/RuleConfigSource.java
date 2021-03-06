package chapter44.v3;

import chapter44.dependence.exception.InvalidRuleConfigException;
import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.model.RuleConfig;
import chapter44.v3.factory.RuleConfigParserFactory;

/**
 * <p>工厂模式</p>
 *
 *
 * 优化方案二：工厂模式
 *
 *
 * 为了让类的职责更加单一、代码更加清晰，我们进一步将 createParser() 函数剥离到一个独立的类中，让这个类只负责对象的创建。
 *
 * RuleConfigParserFactory
 *
 * 这个类就是我们现在要讲的简单工厂模式类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigSource {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);



        // 将 “创建解析类” 的 逻辑 抽象成 独立的‘方法’，将 独立的‘方法’ 进一步封装到一个 独立的类 中。 这个独立类只负责创建，称为工厂类
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);



        if (parser == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
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
