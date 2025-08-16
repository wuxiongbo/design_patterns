package design_patterns.chapter18.config;

import design_patterns.chapter18.util.ConfigSource;
import design_patterns.chapter18.view.Viewer;

import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class MysqlConfig implements Viewer {
    public MysqlConfig(ConfigSource configSource) {

    }

    @Override
    public String outputInPlainText() {

        /*实现。。。*/

        return null;
    }

    @Override
    public Map<String, String> output() {

        /*实现。。。*/

        return null;
    }
}
