package chapter51.demo4;

import chapter51.demo4.external_system.adaptee.B;
import chapter51.demo4.external_system.targets.IA;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
// 在我们的项目中，外部系统A 的使用示例
public class Demo {

    private IA a;

    public Demo(IA a) {
        this.a = a;
    }
    //...




    public static void main(String[] args) {

//        Demo d = new Demo(new A());


        // 外部系统A 替换成 外部系统B

        // 借助BAdaptor，将系统B 适配为 系统A 的接口，适配后，Demo里所有调用IA接口的代码都无需改动，
        Demo d = new Demo(new BAdaptor(new B()));  // 只需要将BAdaptor 注入到Demo即可。
    }

}
