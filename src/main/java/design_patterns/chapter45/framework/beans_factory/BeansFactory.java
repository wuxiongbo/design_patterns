package design_patterns.chapter45.framework.beans_factory;

import design_patterns.chapter45.framework.BeanDefinition;
import design_patterns.chapter45.framework.exception.BeanCreationFailureException;
import design_patterns.chapter45.framework.exception.NoSuchBeanDefinitionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 核心——“工厂类” </p>
 *
 *  BeansFactory 创建对象用到的主要技术点就是 Java 中的反射语法：一种动态加载类和创建对象的机制。
 *
 *  我们知道，JVM 在启动的时候，会根据代码自动地加载类、创建对象。
 *  至于都要加载哪些类、创建哪些对象，这些都是在代码中写死的，或者说提前写好的。
 *
 *  但是，如果某个对象的创建并不是写死在代码中，而是放到配置文件中，
 *  我们需要 在程序运行期间，动态地根据配置文件来加载类、创建对象，
 *  那这部分工作 就没法让 JVM 帮我们自动完成了，我们需要利用 Java 提供的反射语法自己去编写代码。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class BeansFactory {

    // 存储实例化对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 存储bean定义
    // k：BeanDefinition.id， v：BeanDefinition
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();



    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList)  {

        for (BeanDefinition beanDefinition : beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }

        // 非懒加载，则在添加完bean定义后，立即创建对象。
        // 懒加载，则在  getBean 的时候 再创建
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            // 非懒加载 且 单例
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
                try {
                    createBean(beanDefinition);
                } catch (BeanCreationFailureException | NoSuchBeanDefinitionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getBean(String beanId) throws NoSuchBeanDefinitionException, BeanCreationFailureException {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("Bean is not defined: " + beanId);
        }
        return createBean(beanDefinition);
    }

//    @VisibleForTesting
    protected Object createBean(BeanDefinition beanDefinition) throws BeanCreationFailureException, NoSuchBeanDefinitionException {
        // 单例 则从缓存拿。
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }

        Object bean = null;
        try {
            // bean的 class类型
            Class beanClass = Class.forName(beanDefinition.getClassName());
            // bean的 构造方法参数值 列表。
            List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();

            // 无参构造
            if (args.isEmpty()) {
                bean = beanClass.newInstance();
            }
            // 有参构造
            else {
                // 构造方法 入参的 类型
                Class[] argClasses = new Class[args.size()];
                // 构造方法 入参的 值
                Object[] argObjects = new Object[args.size()];
                //
                for (int i = 0; i < args.size(); ++i) {
                    // bean的 构造器参数
                    BeanDefinition.ConstructorArg arg = args.get(i);

                    // 入参 不是引用类型
                    if (!arg.getIsRef()) {
                        argClasses[i] = arg.getType();
                        argObjects[i] = arg.getArg();
                    }
                    // 入参 是引用类型
                    else {
                        // 引用类型的参数值为 refId

                        // 获取引用类型的bean定义。
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.getArg());
                        if (refBeanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.getArg());
                        }

                        // 构造方法 入参的 类型
                        argClasses[i] = Class.forName(refBeanDefinition.getClassName());

                        // 构造方法 入参的 值。   递归调用 createBean
                        argObjects[i] = createBean(refBeanDefinition);
                    }
                }

                // 根据  入参类型 获得构造器对象，
                // 输入  构造器 入参的值  构造对象
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }

        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeanCreationFailureException("", e);
        }

        // 如果是单例，则需要缓存。
        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.getId(), bean);

            return singletonObjects.get(beanDefinition.getId());
        }

        return bean;
    }
}