package the_beauty_of_design_patterns.chapter48.demo0.v2;

import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.IUserController;
import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.UserController;
import the_beauty_of_design_patterns.chapter48.demo0.v2.proxy.UserControllerProxy;

/**
 * <p> 代理模式   UserControllerProxy使用举例 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class Main {
    public static void main(String[] args) {

        // 因为 原始类 和 代理类 实现相同的接口，是基于接口而非实现编程
        // 所以，将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码

        IUserController userController = new UserControllerProxy(new UserController());
        userController.login("手机号","密码");
        userController.register("手机号","密码");

    }
}
