package chapter58.demo1;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public abstract class AbstractClass {

    // 模板方法
    // 包含 “算法骨架” 的方法，就是 “模板方法”
    public final void templateMethod() {
        //...
        method1();
        //...
        method2();
        //...
    }

    // 算法骨架
    // 推迟到子类中实现
    protected abstract void method1();
    protected abstract void method2();

}
