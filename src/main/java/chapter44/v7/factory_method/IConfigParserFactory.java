package chapter44.v7.factory_method;

import chapter44.dependence.config_parser.IRuleConfigParser;
import chapter44.dependence.config_parser.ISystemConfigParser;

/**
 * <p> 抽象工厂 </p>
 *
 * 在简单工厂和工厂方法中，类只有一种分类方式。
 * 比如，在规则配置解析那个例子中，解析器类 只会根据配置文件格式（Json、Xml、Yaml……）来分类。
 *
 * 但是，如果 解析器类 有两种分类方式，
 * 比如，
 *      我们既可以  按照配置文件格式 来分类，
 *      也可以 按照解析的对象（Rule 规则配置还是 System 系统配置） 来分类，
 * 那就会对应下面这 8 个 parser 类。
 *
 *   针对规则配置的解析器：基于接口IRuleConfigParser
 *   JsonRuleConfigParser
 *   XmlRuleConfigParser
 *   YamlRuleConfigParser
 *   PropertiesRuleConfigParser
 *
 *   针对系统配置的解析器：基于接口ISystemConfigParser
 *   JsonSystemConfigParser
 *   XmlSystemConfigParser
 *   YamlSystemConfigParser
 *   PropertiesSystemConfigParser
 *
 * 如果我们未来还需要增加  针对业务配置的解析器（比如 IBizConfigParser） ，那就要再对应地增加 4 个工厂类。
 * 而我们知道，过多的类也会让系统难维护。这个问题该怎么解决呢？
 *
 * 抽象工厂 就是针对这种非常特殊的场景而诞生的。
 * 我们可以让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser 等），而不是只创建一种 parser 对象。
 * 这样就可以有效地减少工厂类的个数。
 *
 *
 * 1. 抽象工厂 相比于v6版，命名更加抽象， 将 IRuleConfigParserFactory 改为 IConfigParserFactory
 * 2. createParser 通过 函数隔离(或称 接口隔离) 成  createRuleParser、createSystemParser。 而不是通过抽取独立的类隔离。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface IConfigParserFactory {

    // 将工厂类的命名 更加 抽象化，
    // 然后，将不同场景的业务逻辑，通过 函数 隔离。

    IRuleConfigParser createRuleParser();


    ISystemConfigParser createSystemParser();

    //此处可以扩展新的parser类型，比如IBizConfigParser

}
