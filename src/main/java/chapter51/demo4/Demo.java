package chapter51.demo4;

import chapter51.demo4.external_system.adaptee.B;
import chapter51.demo4.external_system.targets.A;
import chapter51.demo4.external_system.targets.IA;

/**
 * <p>系统切换</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */

public class Demo {

    private IA a;

    public Demo(IA a) {
        this.a = a;
    }
    //...

    public void func(){
        a.fa();
    }


    public static void main(String[] args) {

        // 在我们的项目中，外部系统A 的使用示例
        Demo a = new Demo(new A());
        a.func();

        // 现在，将 外部系统A 替换成 外部系统B

        // 借助BAdaptor，将系统B 适配为 系统A 的接口，适配后，Demo里所有调用IA接口的代码都无需改动，
        Demo b = new Demo(new B_Adaptor(new B()));  // 只需要将BAdaptor 注入到Demo即可。
        b.func();
    }

}