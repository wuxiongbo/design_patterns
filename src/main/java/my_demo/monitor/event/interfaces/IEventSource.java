package my_demo.monitor.event.interfaces;

/**
 * <p>事件源接口（被观察者，新闻发布者）</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IEventSource {
    // 为事件源 注册 或 新增 监听器
    void addListener(IListener listener);

    // 触发监听器
    void triggerListener(IEvent event);
}
