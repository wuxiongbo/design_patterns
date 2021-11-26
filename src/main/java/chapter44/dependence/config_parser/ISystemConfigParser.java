package chapter44.dependence.config_parser;

import chapter44.dependence.model.SystemConfig;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public interface ISystemConfigParser {
    public SystemConfig parse(String configText);

}
