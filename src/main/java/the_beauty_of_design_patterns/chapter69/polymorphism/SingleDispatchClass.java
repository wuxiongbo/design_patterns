package the_beauty_of_design_patterns.chapter69.polymorphism;

/**
 * <p> 分派 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class SingleDispatchClass {

    // ======= 对 f() 方法  动态分派======================
    // 多态，     “运行时” 实际的类型  决定
    public void polymorphismFunction(ParentClass p) {
        // ParentClass 还是 ChildClass 的 f() 方法，由 运行时 动态决定
        p.f();
    }



    // ====== 对 overloadFunction() 方法 静态分派=============

    // 方法重载，  “编译期” 代码中声明的类型  决定
    public void overloadFunction(ParentClass p) {
        System.out.println("I am overloadFunction( ParentClass p ).");

        p.f(); // 运行时
    }

    // 重载方法
    public void overloadFunction(ChildClass c) {
        System.out.println("I am overloadFunction( ChildClass c ).");

        c.f(); // 运行时
    }

}