package the_beauty_of_design_patterns.chapter45.framework.bean_config_parser;

import the_beauty_of_design_patterns.chapter45.framework.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContent);
}
