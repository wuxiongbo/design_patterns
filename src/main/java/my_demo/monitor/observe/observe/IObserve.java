package my_demo.monitor.observe.observe;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IObserve {
    //    观察者处理被观察者发送过来额信息
    void handleNotify(String message);
}
