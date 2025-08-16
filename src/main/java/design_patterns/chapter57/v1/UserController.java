package design_patterns.chapter57.v1;

import design_patterns.chapter56.dependence.UserService;
import design_patterns.chapter57.v1.observer.RegObserver;

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

    // 一次性设置好 ‘观察者’，之后也不可能动态的修改
    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }


    public Long register(String telephone, String password) {

        // ==============注册用户===================
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);


        // ==============注册以外的附属业务，交给观察者处理===================
        // 把 频繁变更 的业务，交给 ‘观察者’ 去处理
        for (RegObserver observer : regObservers) {
            observer.handleRegSuccess(userId);
        }

        return userId;
    }

}
