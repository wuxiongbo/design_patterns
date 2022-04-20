package chapter50.demo1;

import chapter50.demo1.dependence.IA;

/**
 * <p> 装饰器模式 与 代理模式 的区别： 代理模式 </p>
 *
 * 代理模式中，代理类附加的是跟原始类无关的功能
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class AProxy implements IA {

    // 原始类
    private IA a;

    // 依赖注入
    public AProxy(IA a) {
        this.a = a;
    }


    // 插桩
    @Override
    public void f() {

        // 新添加的代理逻辑， 与 原始类 无关

        // 甚至可以偷天换日。直接 不调用 原始类的方法。
        // a.f();

        // 新添加的代理逻辑， 与 原始类 无关

    }
}
