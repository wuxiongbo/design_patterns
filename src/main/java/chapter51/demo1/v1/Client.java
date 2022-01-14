package chapter51.demo1.v1;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {
    // "类"适配器: 基于 '继承'
    public static void main(String[] args){
        ITarget target = new Adaptor();
        target.f1();
        target.f2();
        target.fc();
    }
}
