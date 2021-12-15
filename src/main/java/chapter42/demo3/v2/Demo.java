package chapter42.demo3.v2;


/**
 * <p>单例模式替代解决方案</p>
 *
 * 依赖注入，解决单例隐藏类之间依赖关系的问题
 *
 * 基于新的使用方式，我们将单例生成的对象，作为参数传递给函数（也可以通过构造函数传递给类的成员变量），可以解决单例隐藏类之间依赖关系的问题。
 *
 * 不过，对于单例存在的其他问题，比如对 OOP 特性、扩展性、可测性不友好等问题，还是无法解决。
 *
 * 所以，如果要完全解决这些问题，我们可能要从根上，寻找其他方式来实现全局唯一类。
 * 实际上，类对象的全局唯一性可以通过多种不同的方式来保证。
 * 我们既可以通过 单例模式 来强制保证，也可以通过  工厂模式、IOC 容器（比如 Spring IOC 容器）来保证，
 * 还可以通过程序员自己来保证（自己在编写代码的时候自己保证不要创建两个类对象）。
 * 这就类似 Java 中内存对象的释放由 JVM 来负责，而 C++ 中由程序员自己负责，道理是一样的。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */
public class Demo {

    // 1. 老的使用方式
    public void demofunction() {
        //...
        long id = IdGenerator.getInstance().getId();
        //...
    }

    // 2. 新的使用方式：依赖注入
    public void demofunction(IdGenerator idGenerator) {
        //...
        long id = idGenerator.getId();
        //...
    }



    public static void main(String[] args){
        // 外部调用demofunction()的时候，传入idGenerator
        IdGenerator idGenerator = IdGenerator.getInstance();

        Demo demo = new Demo();
        demo.demofunction(idGenerator);

    }


}
