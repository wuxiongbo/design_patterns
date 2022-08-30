package chapter69.polymorphism;

/**
 * <p> 调度器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class SingleDispatchClass {

    // 多态，     “运行时” 实际的类型  决定
    public void polymorphismFunction(ParentClass p) {
        p.f();
    }




    // 方法重载，  “编译期” 代码中声明的类型  决定
    public void overloadFunction(ParentClass p) {
        System.out.println("I am overloadFunction(ParentClass p).");
//        p.f();
    }

    // 重载方法
    public void overloadFunction(ChildClass c) {
        System.out.println("I am overloadFunction(ChildClass c).");
//        c.f();
    }

}