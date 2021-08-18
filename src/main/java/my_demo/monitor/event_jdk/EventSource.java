package my_demo.monitor.event_jdk;

import java.util.EventListener;
import java.util.Vector;

/**
 * <p>事件源(被观察者)</p>
 *
 *  三要素：
 *  1.保存监听器
 *  2.维护监听器
 *  3.通知监听器
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class EventSource {

    // 维护一个监听器列表。
    // 如果监听事件源的事件，注册监听器可以加入此列表
    private Vector<EventListener> listenerList = new Vector<>();


    //注册监听器
    public void addListener(EventListener eventListener) {
        listenerList.add(eventListener);
    }

    //注册指定监听器
    public void addCloseWindowListener(EventListener eventListener) {
        System.out.println("关注关闭窗口事件(即，注册 关窗 监听器)");
        listenerList.add(eventListener);
    }


    //删除监听器
    public void removeListener(EventListener eventListener) {
        int i = listenerList.indexOf(eventListener);
        if(i >= 0) {
            listenerList.remove(eventListener);
        }
    }

    // 每 接受一个外部事件，   都会 通知(调用)所有的监听器
    public void notifyListenerEvents(PrintEvent event) {
        for(EventListener moniterListener : listenerList) {
            // 监听器类，被调用handleEvent方法。 对事件进行处理。
            ((Listener)moniterListener).handleEvent(event);
        }
    }
    //方法内创建特定事件，通知(调用)所有的监听器
    public void doCloseWindows() {
        this.notifyListenerEvents(new PrintEvent("closeWindows"));
    }

}
