package chapter45.framework;

import chapter45.framework.exception.BeanCreationFailureException;
import chapter45.framework.exception.NoSuchBeanDefinitionException;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public interface ApplicationContext {
    Object getBean(String beanId) throws BeanCreationFailureException, NoSuchBeanDefinitionException;
}
