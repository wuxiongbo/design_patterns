package chapter71;

import chapter71.Invoker.GameApplication;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/14
 * </pre>
 */
public class Client {

    public static void main(String[] args) {
        GameApplication invoker = new GameApplication();
        invoker.mainloop();
    }

}
