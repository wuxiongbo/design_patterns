package chapter51.demo1.v2;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {

    //  "对象"适配器: 基于 '组合'
    public static void main(String[] args){
        // 原始对象
        Adaptee adaptee = new Adaptee();

        // 依赖注入 原始对象
        ITarget target = new Adaptor(adaptee);
        target.f1();
        target.f2();
        target.fc();
    }

}
