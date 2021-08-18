package my_demo.monitor.event.interfaces;

/**
 * <p>事件源接口（被观察者）</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IListenerable {
    // 新增
    void addListener(IListener listener);

    //为事件源注册监听器
    void setListener(IListener listener);

    //触发监听器
    void triggerListener(IEvent event);

}
