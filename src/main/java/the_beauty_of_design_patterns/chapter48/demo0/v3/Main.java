package the_beauty_of_design_patterns.chapter48.demo0.v3;

import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.IUserController;
import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.UserController;

/**
 * <p>代理模式 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        //MetricsCollectorProxy使用举例
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
    }
}
