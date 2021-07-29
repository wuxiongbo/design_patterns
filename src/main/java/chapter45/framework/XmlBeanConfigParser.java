package chapter45.framework;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class XmlBeanConfigParser implements BeanConfigParser {

    /**
     * 将配置文件解析成 bean定义
     * @param inputStream
     * @return
     */
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        String content = null;

        // TODO: 输入流转化为文本类型

        return parse(content);
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        // TODO: 解析xml格式的字符串，转为 bean定义

        return beanDefinitions;
    }

}