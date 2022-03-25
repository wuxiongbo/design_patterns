package chapter57.v2;

import chapter56.demo2.v2.observer.RegObserver;
import chapter56.dependence.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * <p> 改版 </p>
 *
 * 异步 第二种方式： 将异步放在 被观察者，即 本UserController
 *
 * 尽管利用了线程池解决了第一种实现方式的问题，但线程池、异步执行逻辑都耦合在了 register() 函数中，增加了这部分业务代码的维护成本。
 *
 *
 * 如果我们的需求更加极端一点，需要在  同步阻塞  和  异步非阻塞  之间灵活切换，那就要不停地修改 UserController 的代码。
 *
 * 除此之外，如果在项目中，不止一个业务模块需要用到 异步非阻塞观察者模式 ，那这样的代码实现也无法做到复用。
 * 我们知道，框架的作用有：隐藏实现细节，降低开发难度，做到代码复用，解耦业务与非业务代码，让程序员聚焦业务开发。
 *
 * 针对异步非阻塞观察者模式，我们也可以将它抽象成框架来达到这样的效果，而这个框架就是我们这节课要讲的 EventBus。
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class UserController {

    private UserService userService; // 依赖注入

    private List<RegObserver> regObservers = new ArrayList<>();

    private Executor executor;

    public UserController(Executor executor) {
        this.executor = executor;
    }

    // 一次性设置好 ‘观察者’，之后也不可能动态的修改
    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }


    public Long register(String telephone, String password) {


        //省略输入参数的校验代码

        // ==========注册用户==================
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);


        // ==========异步通知 事件==================
        for (RegObserver observer : regObservers) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observer.handleRegSuccess(userId);
                }
            });
        }

        return userId;
    }
}
