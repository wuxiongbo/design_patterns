package chapter56.demo2.v2;

import chapter56.demo2.v2.observer.RegObserver;
import chapter56.dependence.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 描述类的信息 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class UserController {

    private UserService userService; // 依赖注入


    private List<RegObserver> regObservers = new ArrayList<>();
    // 一次性设置好，之后也不可能动态的修改
    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }


    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        // 把频繁变化的业务交给观察者去处理
        for (RegObserver observer : regObservers) {
            observer.handleRegSuccess(userId);
        }

        return userId;
    }

}
