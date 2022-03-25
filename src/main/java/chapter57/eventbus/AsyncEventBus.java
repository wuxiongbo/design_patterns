package chapter57.eventbus;

import java.util.concurrent.Executor;

/**
 * <p> 5.AsyncEventBus </p>
 * 有了 EventBus，
 * AsyncEventBus 的实现就非常简单了。
 *
 * 为了实现异步非阻塞的观察者模式，它就不能再继续使用 MoreExecutors.directExecutor() 了，而是需要在构造函数中，由调用者注入线程池。
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }

}
