package my_demo.monitor.event_jdk.eventsource;

import my_demo.monitor.event_jdk.event.CloseEvent;
import my_demo.monitor.event_jdk.event.PrintEvent;
import my_demo.monitor.event_jdk.listener.IListener;
import my_demo.monitor.event_jdk.listener.impl.CloseWindowsListener;
import my_demo.monitor.event_jdk.listener.impl.OpenWindowsListener;
import my_demo.monitor.event_jdk.listener.impl.WindowsListener;

import java.util.EventListener;
import java.util.Vector;

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
    private Vector<EventListener> listenerList = new Vector<>();


    //注册监听器
    public void addListener(EventListener eventListener) {
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
    public void notifyListenerEvents(PrintEvent event) {
        for(EventListener eventListener : listenerList) {
            // 监听器类，被调用handleEvent方法。 对事件进行处理。
            try {
                ((IListener) eventListener).handleEvent(event);
            } catch (ClassCastException e) {
                // ignore
            }
        }
    }


    //注册 指定监听器
    public void addCloseWindowListener(EventListener eventListener) {
        if(eventListener instanceof CloseWindowsListener){
            System.out.println("注册 关闭窗口监听器，以关注 关闭窗口事件");
            listenerList.add(eventListener);
        }
    }

    public void addOpenWindowListener(EventListener eventListener) {
        if(eventListener instanceof OpenWindowsListener){
            System.out.println("注册 打开窗口监听器，以关注 打开窗口事件");
            listenerList.add(eventListener);
        }
    }



    // "事件源" 产生 "关窗事件" 并通知 (调用)所有的监听器
    public void doCloseWindows() {
        PrintEvent closeWindowsEvent = new CloseEvent(this);
        this.notifyListenerEvents(closeWindowsEvent);
    }

}
