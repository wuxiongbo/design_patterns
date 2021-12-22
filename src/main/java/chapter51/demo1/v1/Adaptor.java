package chapter51.demo1.v1;

/**
 * <p> 适配器 </p>
 *
 * "类"适配器: 基于 '继承'
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Adaptor extends Adaptee implements ITarget {

    // 原始方法fa() 转 f1()
    @Override
    public void f1() {
        super.fa();
    }

    // 原始方法fb()弃用，重新实现 f2()
    @Override
    public void f2() {
        //...重新实现f2()...
        System.out.println("f2()");
    }

    // 复用 原始方法fc()
    // 这里fc()不需要实现，直接继承自 Adaptee，这是跟 对象适配器 最大的不同点

}
