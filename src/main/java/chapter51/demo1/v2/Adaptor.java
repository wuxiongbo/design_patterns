package chapter51.demo1.v2;

import chapter51.demo1.adaptee.Adaptee;
import chapter51.demo1.itarget.ITarget;

/**
 * <p> 对象 适配器 </p>
 *
 * "对象"适配器: 基于 '组合'
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Adaptor

        // 这里少了“继承” ，改用 ‘组合’ 。  “对象” 适配器， 使用 “组合” 的方式， 获取 被适配者的 能力。缺点是，不管是否同名，全部接口都要适配一遍

        implements ITarget {


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


    // 无法复用 原始方法fc()，需要再实现一遍
    @Override
    public void fc() {
        adaptee.fc(); // 将实现 委托给 Adaptee
    }

}
