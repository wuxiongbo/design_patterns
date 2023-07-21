package my_demo.monitor.event_jdk.eventsource;

import my_demo.monitor.event_jdk.event.CloseEvent;
import my_demo.monitor.event_jdk.event.OpenEvent;
import my_demo.monitor.event_jdk.event.base.PrintEvent;
import my_demo.monitor.event_jdk.listener.IListener;

import java.util.EventListener;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>事件源(被观察者)</p>
 *
 *  “事件源”三要素：
 *      1.“保存” 监听器
 *      2.“维护” 监听器 列表
 *      3.“通知” 监听器
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class WindowsEventSource {

    // 维护一个监听器列表。
    // 如果监听事件源的事件，注册监听器可以加入此列表
    private final CopyOnWriteArrayList<EventListener> listenerList = new CopyOnWriteArrayList<>();


    //注册监听器
    public void addWindowsListener(EventListener eventListener) {
        listenerList.add(eventListener);
    }
    //删除监听器
    public void removeListener(EventListener eventListener) {
        int i = listenerList.indexOf(eventListener);
        if(i >= 0) {
            listenerList.remove(eventListener);
        }
    }


    // 触发事件
    // 每触发一次 外部事件， 都会 通知(调用)所有的 “监听器”
    // 参考： org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener
    @SuppressWarnings("unchecked")
    public void notifyListenerEvents(PrintEvent event) {
        for(EventListener eventListener : listenerList) {
            // 监听器类，被调用handleEvent方法。 对事件进行处理。
            try {
                IListener.class.cast(eventListener).handleEvent(event);
            } catch (ClassCastException e) {
                // ignore
                // 事件不匹配，则会类型转化异常
                System.out.println("监听器 "+ eventListener.toString() + " 对 " + event.eventType() +" 事件不感兴趣，忽略");
            }
        }
    }

    // "事件源" 产生 "关窗事件" ，然后 通知 所有的监听器
    public void doCloseWindows() {
        CloseEvent closeWindowsEvent = new CloseEvent(this);
        System.out.println("=====触发事件=====");
        this.notifyListenerEvents(closeWindowsEvent);
    }

    // 由 ‘事件源’ 触发事件：
    //    1. 产生 openWindows事件
    //    2. 对 openWindows事件 感兴趣的listener将会监听到 该事件，然后执行相关操作
    public void doOpenWindows() {
        OpenEvent openWindowsEvent = new OpenEvent(this);
        System.out.println("=====触发事件=====");
        this.notifyListenerEvents(openWindowsEvent);
    }
}
