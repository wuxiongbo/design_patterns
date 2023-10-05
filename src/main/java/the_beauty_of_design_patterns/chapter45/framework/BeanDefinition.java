package the_beauty_of_design_patterns.chapter45.framework;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> bean定义 </p>
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

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }


    public enum Scope {
        SINGLETON,
        PROTOTYPE
    }



    //在下面的 ConstructorArg 类中，
    // 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置；
    // 当 isRef 为 false 的时候，arg、type 都需要设置。
    // 请根据这个需求，完善 ConstructorArg 类。
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
    }

/*    public static class ConstructorArg {
        private boolean isRef;
        private Class type;
        private Object arg;
        // TODO: 待完善...

        private ConstructorArg(Builder builder) {
            this.isRef = builder.isRef;
            this.type = builder.type;
            this.arg = builder.arg;
        }

        public static class Builder {
            private boolean isRef;
            private Class type;
            private Object arg;

            public ConstructorArg build() {
                if (arg == null){
                        throw new IllegalArgumentException("arg must be set");
                }

                if (!isRef) {
                    if (type == null){
                        throw new IllegalArgumentException("type must be set when isRef is false");
                    }

                    // 参数类型 与参数值类型要一致。
                    if (type != arg.getClass()) {
                        throw new IllegalArgumentException("...");
                    }

                } else {
                    if (!(arg instanceof String)) {
                        throw new IllegalArgumentException("arg must be a String instance when isRef is true");
                    }
                }
                return new ConstructorArg(this);
            }

            public Builder setIsRef(boolean isRef){
                this.isRef = isRef;
                return this;
            }

            public Builder setType(Class type) {
                this.type = type;
                return this;
            }

            public Builder setObject(Object arg) {
                this.arg = arg;
                return this;
            }
        }
    }*/


}
