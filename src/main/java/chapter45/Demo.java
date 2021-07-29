package chapter45;

import chapter45.bean.RateLimiter;
import chapter45.framework.ApplicationContext;
import chapter45.framework.ClassPathXmlApplicationContext;
import chapter45.framework.exception.BeanCreationFailureException;
import chapter45.framework.exception.NoSuchBeanDefinitionException;

/**
 * <p>工厂模式的应用-- 简单的个DI容器</p>
 *
 * 一个简单的 DI 容器的核心功能一般有三个：
 *      配置解析、
 *      对象创建 和
 *      对象生命周期管理。
 *
 * 编写思路：
 * 1. 最小原型设计
 * Demo、xml
 *
 * 2. 提供执行入口
 * ApplicationContext、 ClassPathXmlApplicationContext
 *
 * 3.配置文件解析
 * BeanConfigParser、 XmlBeanConfigParser
 *
 * 4.核心工厂类设计
 * BeansFactory
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class Demo {
    public static void main(String[] args) throws NoSuchBeanDefinitionException, BeanCreationFailureException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");

        rateLimiter.test();
        //...
    }
}
