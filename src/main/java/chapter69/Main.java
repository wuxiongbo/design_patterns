package chapter69;

/**
 * <p> 编译时 重载， 运行时 多态 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        SingleDispatchClass dispatch = new SingleDispatchClass();

        ParentClass p = new ChildClass();

        dispatch.polymorphismFunction(p);  // 执行 哪个对象的 f()方法，由 对象 “运行时” 的实际类型决定


        dispatch.overloadFunction(p);      // 执行 dispatch对象 的哪个方法，由 参数对象 “编译时”的声明类型决定

    }
}
/*
代码执行结果:
I am ChildClass's f().
I am overloadFunction(ParentClass p).
 */