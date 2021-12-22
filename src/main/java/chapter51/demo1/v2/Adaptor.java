package chapter51.demo1.v2;

/**
 * <p> 适配器 </p>
 *
 * "对象"适配器: 基于 '组合'
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Adaptor implements ITarget {

    // 聚合
    private Adaptee adaptee;

    // 依赖注入
    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    // 原始方法fa() 转 f1()
    @Override
    public void f1() {
        adaptee.fa();  // 委托给 Adaptee
    }

    // 原始方法fb()弃用，重新实现 f2()
    @Override
    public void f2() {
        //...重新实现f2()...
        System.out.println("f2()");
    }


    // 复用 原始方法fc()
    @Override
    public void fc() {
        adaptee.fc(); // 复用，同样需要再实现一遍。 实现 委托给 Adaptee
    }

}
