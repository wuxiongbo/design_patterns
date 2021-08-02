package chapter45.framework;

import lombok.Data;

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
@Data
public class BeanDefinition {

    // 类别名。 作为唯一标识
    private String id;

    // 类名
    private String className;

    // 构造器参数
    private List<ConstructorArg> constructorArgs = new ArrayList<>();

    // 单例、多例。默认单例
    private Scope scope = Scope.SINGLETON;

    // 是否懒加载。默认不是懒加载
    // 如果 lazy-init=true，对象在真正被使用到的时候（比如：BeansFactory.getBean(“userService”)）才被被创建；
    // 如果 lazy-init=false，对象在应用启动的时候就事先创建好。
    private boolean lazyInit = false;


    // 省略必要的getter/setter/constructors

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }


    public static enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    @Data
    public static class ConstructorArg {
        // 是否为引用类型
        private boolean isRef;
        // 参数类型
        private Class type;
        // 参数值。
        private Object arg;

        public boolean getIsRef() {
            return isRef;
        }

        public boolean setIsRef(boolean isRef) {
            return this.isRef = isRef;
        }
        // 省略必要的getter/setter/constructors
    }
}
