package my_demo.monitor.observe.observe;

/**
 * <p>第二个观察者</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Observe2 implements IObserve {
    @Override
    public void handleNotify(String message) {
        System.out.println("2号观察者接受的消息"+message);
    }
}