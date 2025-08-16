package design_patterns.chapter50.demo1;

import design_patterns.chapter50.demo1.dependence.IA;

/**
 * <p> 装饰器模式 与 代理模式 的区别： 装饰器模式 </p>
 *
 * 装饰器模式中，装饰器类附加的是 跟原始类相关的增强功能。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class ADecorator implements IA {

    // 原始类
    private IA a;

    // 依赖注入
    public ADecorator(IA a) {
        this.a = a;
    }

    // 业务增强
    @Override
    public void f() {

        // 功能增强代码， 与 原始类 相关
        a.f();
        // 功能增强代码， 与 原始类 相关

    }
}