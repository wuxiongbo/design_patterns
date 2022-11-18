package my_demo.monitor.observe.observe;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IObserver {

    // 观察者 处理 被观察者 发送过来的信息
    void handleNotify(String message);

}
