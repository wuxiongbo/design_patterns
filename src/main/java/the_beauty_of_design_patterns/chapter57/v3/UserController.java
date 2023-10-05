package the_beauty_of_design_patterns.chapter57.v3;

import the_beauty_of_design_patterns.chapter56.dependence.UserService;

import the_beauty_of_design_patterns.chapter57.eventbus.AsyncEventBus;
import the_beauty_of_design_patterns.chapter57.eventbus.EventBus;
//import com.google.common.eventbus.AsyncEventBus;
//import com.google.common.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * <p> 使用 事件总线 </p>
 *
 * EventBus 翻译为“事件总线”，它提供了  实现 ‘观察者模式’ 的 骨架代码。
 *
 * 我们可以基于此框架，非常容易地在自己的业务场景中实现观察者模式，不需要从零开始开发。
 * 其中，Google Guava EventBus 就是一个比较著名的 EventBus 框架，它不仅仅支持  异步 非阻塞 模式，
 * 同时也支持  同步 阻塞 模式
 *
 *
 * 现在，我们就通过例子来看一下，Guava EventBus 具有哪些功能。
 * 还是上节课那个用户注册的例子，我们用 Guava EventBus 重新实现一下，代码如下所示：
 *
 *
 * 利用 EventBus 框架实现的观察者模式，跟从零开始编写的观察者模式相比，从大的流程上来说，实现思路大致一样，都需要定义 Observer，
 * 并且通过 register() 函数注册 Observer，
 * 也都需要通过调用  某个函数（比如，EventBus 中的 post() 函数）来给 Observer 发送消息（在 EventBus 中消息被称作事件 event）。
 * 但在实现细节方面，它们又有些区别。
 *
 * 基于 EventBus，我们不需要定义 Observer 接口，任意类型的对象都可以注册到 EventBus 中，
 * 通过 @Subscribe 注解 来标明 类中哪个函数可以接收'被观察者'发送的消息。
 *
 *
 *
 *
 * 我们用 Guava EventBus 重新实现了 UserController，实际上，代码还是不够解耦。
 * UserController 还是耦合了很多跟观察者模式相关的非业务代码，比如创建线程池、注册 Observer。
 *
 * 为了让 UserController 更加聚焦在业务功能上，有什么重构的方法呢？
 * 答案：代理模式
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class UserController {

    private UserService userService; // 依赖注入

    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;


    public UserController() {
        // 同步阻塞模式
        //eventBus = new EventBus();
        // 异步非阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }


    /**
     * 注册观察者
     *
     * EventBus 类提供了 register() 函数， 用来注册观察者。
     * 具体的函数定义如下所示。
     * 它可以接受任何类型（Object）的观察者。
     *
     * 在经典的 观察者模式 的实现中，register() 函数 必须接受 实现了 "同一Observer 接口"的类对象。
     *
     * 而 EventBus 框架，则不要求，而是通过 “@注解” 识别。
     *
     * 相对于 register() 函数，
     * unregister() 函数，则用来从 EventBus 中删除某个观察者。这里就不多解释了
     *
     * @param observers
     */
    public void setRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    /**
     * EventBus 类提供了 post() 函数，用来给 观察者 发送消息。
     *
     * 跟经典的 观察者模式 的不同之处在于：
     *   当我们调用 post() 函数，发送消息的时候，
     *      并非 把消息发送给  “所有”的 观察者，
     *      而是 发送给  “可匹配”的 观察者。
     *
     *   这里，所谓 “可匹配” 指的是 类型匹配。即，接收消息类型 是 发送消息类型（post 函数定义中的 event） 的 父类。
     *
     * @param telephone
     * @param password
     * @return
     */
    public Long register(String telephone, String password) {


        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);


        // 事件总线  通知 “观察者” 。
        eventBus.post(userId);

        return userId;
    }
}
