package the_beauty_of_design_patterns.chapter48.demo0.v2.proxy;

import the_beauty_of_design_patterns.chapter48.dependence.MetricsCollector;
import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.IUserController;
import the_beauty_of_design_patterns.chapter48.demo0.v2.controller.UserController;
import the_beauty_of_design_patterns.chapter48.dependence.RequestInfo;
import the_beauty_of_design_patterns.chapter48.dependence.UserVo;

/**
 * <p>静态代理： 接口</p>
 *
 * 静态代理存在的问题：
 *   一: 我们需要在代理类中，将原始类中的所有的方法，都重新实现一遍，并且为每个方法都附加相似的代码逻辑。
 *   二: 如果要添加的附加功能的类有不止一个，我们需要针对每个类都创建一个代理类。
 *       如果有 50 个要添加附加功能的原始类，那我们就要创建 50 个对应的代理类。
 *       这会导致项目中类的个数成倍增加，增加了代码维护成本。
 *       并且，每个代理类中的代码都有点像模板式的“重复”代码，也增加了不必要的开发成本。
 *
 * 那这个问题怎么解决呢？   动态代理
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class UserControllerProxy implements IUserController {

    // 增强业务
    private MetricsCollector metricsCollector;

    // 委托对象(被代理对象)
    private UserController userController;


    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public UserVo login(String telephone, String password) {

        long startTimestamp = System.currentTimeMillis();



        // 委托
        UserVo userVo = userController.login(telephone, password);



        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        // 委托
        UserVo userVo = userController.register(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }
}
