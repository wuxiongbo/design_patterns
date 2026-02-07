package design_patterns.chapter51.demo4;

import design_patterns.chapter51.demo4.external_system.targets.IA;
import design_patterns.chapter51.demo4.external_system.adaptee.IB;

/**
 * <p>适配器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
// 适配器 将 外部系统B 适配 为 外部系统A 的接口
public class B_Adaptor implements IA {

    private final IB b;

    public B_Adaptor(IB b) {
        this.b = b;
    }

    @Override
    public void fa() {
        //...
        b.fb();
    }
}