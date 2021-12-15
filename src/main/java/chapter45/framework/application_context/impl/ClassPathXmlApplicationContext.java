package chapter45.framework.application_context.impl;

import chapter45.framework.BeanDefinition;
import chapter45.framework.beans_factory.BeansFactory;
import chapter45.framework.application_context.ApplicationContext;
import chapter45.framework.bean_config_parser.BeanConfigParser;
import chapter45.framework.bean_config_parser.impl.XmlBeanConfigParser;
import chapter45.framework.exception.BeanCreationFailureException;
import chapter45.framework.exception.NoSuchBeanDefinitionException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p> 入口 </p>
 *
 * ClassPathXmlApplicationContext 负责组装 BeansFactory 和 BeanConfigParser 两个功能类，
 *
 * ApplicationContext 的 执行流程：
 * 1. 从 classpath 中加载 XML 格式的配置文件，
 * 2. 使用 BeanConfigParser 解析配置文件为统一的 BeanDefinition 格式，
 * 3. 然后，使用 BeansFactory ， 根据 BeanDefinition 来创建对象。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    // 用于 创建对象、 保存 BeanDefinition
    private BeansFactory beansFactory;

    // 用于 将配置文件 解析为 BeanDefinition
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation)  {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinitions(configLocation);
    }

    // 加载配置文件，并交给配置解析类，去解析
    private void loadBeanDefinitions(String configLocation)  {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/" + configLocation);
            if (in == null) {
                throw new RuntimeException("Can not find config file: " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);

            // 在这里面判断是否在添加bean定义后实例化。
            beansFactory.addBeanDefinitions(beanDefinitions);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO: log error
                }
            }
        }
    }

    @Override
    public Object getBean(String beanId) {
        try {

            return beansFactory.getBean(beanId);

        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        } catch (BeanCreationFailureException e) {
            e.printStackTrace();
        }

        return null;
    }
}