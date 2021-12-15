package chapter44.v5.factory_method;

import chapter44.dependence.config_parser.IRuleConfigParser;

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
