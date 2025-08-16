package design_patterns.chapter44.demo3.v6.factory_method;

import design_patterns.chapter44.dependence.config_parser.IRuleConfigParser;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface IRuleConfigParserFactory {

    IRuleConfigParser createParser();
}
