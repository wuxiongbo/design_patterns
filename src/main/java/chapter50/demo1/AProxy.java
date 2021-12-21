package chapter50.demo1;

import chapter50.demo1.dependence.IA;

/**
 * <p> 代理模式 </p>
 *
 * 代理模式中，代理类附加的是跟原始类无关的功能
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class AProxy implements IA {
    private IA a;
    public AProxy(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 新添加的代理逻辑， 与 原始类 无关
        a.f();
        // 新添加的代理逻辑， 与 原始类 无关
    }
}
