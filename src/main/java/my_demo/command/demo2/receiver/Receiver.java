package my_demo.command.demo2.receiver;

/**
 * <p>接收者</p>
 *
 * 用于封装 指令的具体执行逻辑
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Receiver {

    public void actionA() {
        System.out.println("接收者的 actionA()方法被调用...");
    }

    public void actionB() {
        System.out.println("接收者的 actionB()方法被调用...");
    }

}
