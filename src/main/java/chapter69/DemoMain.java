package chapter69;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class DemoMain {
    public static void main(String[] args) {
        SingleDispatchClass demo = new SingleDispatchClass();

        ParentClass p = new ChildClass();

        demo.polymorphismFunction(p);  // p: 执行哪个对象的方法，由对象的实际类型决定

        demo.overloadFunction(p);   // demo: 执行对象的哪个方法，由参数对象的声明类型决定

    }
}
/*
代码执行结果:
I am ChildClass's f().
I am overloadFunction(ParentClass p).
 */