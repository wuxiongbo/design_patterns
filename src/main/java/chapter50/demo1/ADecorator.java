package chapter50.demo1;

import chapter50.demo1.dependence.IA;

/**
 * <p> 装饰器模式 </p>
 *
 * 装饰器模式中，装饰器类附加的是跟原始类相关的增强功能。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class ADecorator implements IA {
    private IA a;
    public ADecorator(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 功能增强代码， 与 原始类 相关
        a.f();
        // 功能增强代码， 与 原始类 相关
    }
}