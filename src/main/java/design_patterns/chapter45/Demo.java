package design_patterns.chapter45;

import design_patterns.chapter45.bean.RateLimiter;
import design_patterns.chapter45.bean.RedisCounter;
import design_patterns.chapter45.framework.application_context.ApplicationContext;
import design_patterns.chapter45.framework.application_context.impl.ClassPathXmlApplicationContext;
import design_patterns.chapter45.framework.exception.BeanCreationFailureException;
import design_patterns.chapter45.framework.exception.NoSuchBeanDefinitionException;

/**
 * <p> “工厂模式” 的应用-- 简单的个DI容器</p>
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
 * 1）接口  2）实现
 * ApplicationContext、 ClassPathXmlApplicationContext
 *
 * 3. 配置文件解析
 * BeanConfigParser、 XmlBeanConfigParser
 *
 * 4. 核心工厂类设计
 * BeansFactory
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class Demo {
    public static void main(String[] args) throws NoSuchBeanDefinitionException, BeanCreationFailureException {
        // 非懒加载  初始化的时候 实例化bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        // 懒加载  getBean 的时候再初始化
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        rateLimiter.test();

        RedisCounter redisCounter = rateLimiter.getRedisCounter();
        System.out.println(redisCounter);

        //...


    }
}
