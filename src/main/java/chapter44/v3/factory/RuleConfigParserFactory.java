package chapter44.v3.factory;

import chapter44.dependence.config_parser.rule.JsonRuleConfigParser;
import chapter44.dependence.config_parser.rule.PropertiesRuleConfigParser;
import chapter44.dependence.config_parser.rule.XmlRuleConfigParser;
import chapter44.dependence.config_parser.rule.YamlRuleConfigParser;
import chapter44.dependence.config_parser.IRuleConfigParser;

/**
 * <p> 简单工厂模式类  的第一种实现方法</p>
 *  第一种实现方法  每次都返回新创建的对象
 *
 *
 * 每次调用 RuleConfigParserFactory 的 createParser() 的时候，都要创建一个新的 parser。
 *
 * 待改进：
 * 实际上，如果 parser 可以复用，为了节省内存和对象创建的时间，我们可以将 parser 事先创建好缓存起来。
 * 当调用 createParser() 函数的时候，我们从缓存中取出 parser 对象直接使用。
 *
 * 关于if else 优化：
 * 本实现中，有一组 if 分支判断逻辑，是不是应该用多态或其他设计模式来替代呢？
 * 实际上，如果 if 分支并不是很多，代码中有 if 分支也是完全可以接受的。
 * 应用 多态 或 设计模式 来替代 if 分支判断逻辑，也并不是没有任何缺点的，它虽然提高了代码的扩展性，更加符合开闭原则，
 * 但也增加了类的个数，牺牲了代码的可读性。
 *
 * 总结:
 * 尽管简单工厂模式的代码实现中，有多处 if 分支判断逻辑，违背开闭原则，
 * 但权衡扩展性和可读性，这样的代码实现在大多数情况下（比如，不需要频繁地添加 parser，也没有太多的 parser）是没有问题的。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class RuleConfigParserFactory {

    public static IRuleConfigParser createParser(String configFormat) {
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
