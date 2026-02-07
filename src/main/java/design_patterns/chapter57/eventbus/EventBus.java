package design_patterns.chapter57.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * <p>4.EventBus</p>
 *
 * EventBus 实现的是  阻塞同步的观察者模式。
 *
 *
 * 看代码你可能会有些疑问，这明明就用到了线程池 Executor 啊。
 * 实际上，MoreExecutors.directExecutor() 是 Google Guava 提供的工具类，看似是多线程，实际上是单线程。
 * 之所以要这么实现，主要还是为了跟 AsyncEventBus {@link AsyncEventBus}统一代码逻辑，做到代码复用。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */

public class EventBus {

    private Executor executor;

    private final ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        this(MoreExecutors.directExecutor());
    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    // 注册 观察者
    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        // 查找匹配的事件
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);

        // 异步通知
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }

    }
}