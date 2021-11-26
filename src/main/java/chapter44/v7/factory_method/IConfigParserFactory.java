package chapter44.v7.factory_method;

import chapter44.dependence.interface1.IRuleConfigParser;
import chapter44.dependence.interface1.ISystemConfigParser;

/**
 * <p>描述类的信息</p>
 *
 * 1.相比于v6版更加抽象， 将 IRuleConfigParserFactory 改为 IConfigParserFactory
 * 2. createParser 通过函数隔离(或称 接口隔离) 成  createRuleParser、createSystemParser
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface IConfigParserFactory {

    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();

    //此处可以扩展新的parser类型，比如IBizConfigParser

}
