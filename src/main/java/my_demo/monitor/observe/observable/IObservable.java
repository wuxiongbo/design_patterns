package my_demo.monitor.observe.observable;

//引入观察者接口
import my_demo.monitor.observe.observe.IObserver;

/**
 * <p>被观察者接口</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IObservable {
    //    添加观察者
    void addObserver(IObserver observable);
    //    删除观察者
    void removeObserver(IObserver observable);
    //    向观察者发送信息
    void notifyObservers(String message);
}
