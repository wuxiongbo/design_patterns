package design_patterns.chapter51.demo4.external_system.adaptee;

/**
 * <p>被适配者</p>
 *  外部系统B
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class B implements IB {
    //...
    @Override
    public void fb() {
        //...
        System.out.println("B.fb()");
    }
}
