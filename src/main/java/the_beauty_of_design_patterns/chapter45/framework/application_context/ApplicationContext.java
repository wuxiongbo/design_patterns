package the_beauty_of_design_patterns.chapter45.framework.application_context;

import the_beauty_of_design_patterns.chapter45.framework.exception.BeanCreationFailureException;
import the_beauty_of_design_patterns.chapter45.framework.exception.NoSuchBeanDefinitionException;

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
