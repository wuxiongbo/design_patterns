package the_beauty_of_design_patterns.chapter51.demo1.adaptor.v1;

import the_beauty_of_design_patterns.chapter51.demo1.adaptee.Adaptee;
import the_beauty_of_design_patterns.chapter51.demo1.itarget.ITarget;

/**
 * <p> 类 适配器 </p>
 * <p>
 * "类"适配器: 基于 '继承'
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Adaptor

        extends Adaptee  // “类” 适配器 ， 采用 “继承” 的方式，继承 被适配者，以 获取 被适配者 的能力。  从而避免 大量同名接口 需要实现。

        implements ITarget {


    // 情况1：原始方法fa() 转 f1()
    @Override
    public void f1() {
        super.fa();
    }

    // 情况2：原始方法fb()弃用，重新实现 f2()
    @Override
    public void f2() {
        //...重新实现f2()...
        System.out.println("f2()");
    }

    // 情况3：复用 原始方法fc()
    // 这里fc()不需要实现，直接继承自 Adaptee，这是跟 对象适配器 最大的不同点

}
