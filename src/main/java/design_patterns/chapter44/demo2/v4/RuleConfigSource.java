package design_patterns.chapter44.demo2.v4;

import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;
import design_patterns.chapter44.dependence.exception.InvalidRuleConfigException;
import design_patterns.chapter44.dependence.model.RuleConfig;
import design_patterns.chapter44.demo2.v4.factory.RuleConfigParserFactory;

/**
 * <p>工厂模式(改进版)</p>
 *
 * 优化方案三：工厂模式(改进版)，提前实例化。 也可使用jdk8 新特性懒加载。
 *
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
        RuleConfig ruleConfig = parser.parse(configText); //从ruleConfigFilePath文件中读取配置文本到configText中
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }



}
