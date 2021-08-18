package my_demo.monitor.event.interfaces;

/**
 * <p>监听器接口（观察者）</p>
 *    作用：处理消息（事件）
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IListener {
    void handleEvent(IEvent event);
}
