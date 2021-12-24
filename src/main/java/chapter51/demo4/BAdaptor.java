package chapter51.demo4;

import chapter51.demo4.external_system.targets.IA;
import chapter51.demo4.external_system.adaptee.IB;

/**
 * <p>适配器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
// 将 外部系统B 适配 为 外部系统A 的接口
public class BAdaptor implements IA {

    private IB b;

    public BAdaptor(IB b) {
        this.b = b;
    }

    @Override
    public void fa() {
        //...
        b.fb();
    }
}