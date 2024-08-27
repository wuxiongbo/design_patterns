package my_demo.monitor.event_jdk.eventsource;

import my_demo.monitor.event_jdk.event.CloseEvent;
import my_demo.monitor.event_jdk.event.OpenEvent;
import my_demo.monitor.event_jdk.event.base.PrintEvent;
import my_demo.monitor.event_jdk.listener.IListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>事件源(被观察者)</p>
 * <p>
 * “事件源”三要素：
 * 1.“保存” 监听器
 * 2.“维护” 监听器 列表
 * 3.“通知” 监听器
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class WindowsEventSource {

    // 维护一个监听器列表。
    // 如果监听事件源的事件，注册监听器可以加入此列表
    private final CopyOnWriteArrayList<IListener<?>> listenerList = new CopyOnWriteArrayList<>();


    //注册监听器
    public void addWindowsListener(IListener<?> eventListener) {
        listenerList.add(eventListener);
    }
    //删除监听器
    public void removeListener(IListener<?> eventListener) {
        int i = listenerList.indexOf(eventListener);
        if (i >= 0) {
            listenerList.remove(eventListener);
        }
    }

    public final void pushEvent(Object event){
        PrintEvent printEvent = (PrintEvent)event;
        new Multicaster(listenerList).multicastEvent(printEvent);
    }


    // "事件源" 产生 "关窗事件" ，然后 通知 所有的监听器
    public void doCloseWindows() {
        CloseEvent closeWindowsEvent = new CloseEvent(this);
        System.out.println("=====触发事件=====");
        this.pushEvent(closeWindowsEvent);
    }

    // 由 ‘事件源’ 触发事件：
    //    1. 产生 openWindows事件
    //    2. 对 openWindows事件 感兴趣的listener将会监听到 该事件，然后执行相关操作
    public void doOpenWindows() {
        OpenEvent openWindowsEvent = new OpenEvent(this);
        System.out.println("=====触发事件=====");
        this.pushEvent(openWindowsEvent);
    }

    /**
     * 多播器
     */
    public static class Multicaster {
        private final List<IListener<?>> listenerList;

        public Multicaster(List<IListener<?>> listenerList) {
            this.listenerList = listenerList;
        }

        // 触发事件(广播事件)
        // 每触发一次 外部事件， 都会 通知(调用)所有的 “监听器”
        // 参考： org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener
        public void multicastEvent(final PrintEvent event) {

            for (IListener<?> eventListener : listenerList) {
                // 这里可以用线程池，改造为异步事件

                invokeListener(event, eventListener);
            }

        }

        private void invokeListener(PrintEvent event, IListener<?> eventListener) {
            doInvokeListener(event,eventListener);
        }

        /**
         * 将监听器的泛型擦除
         * @param event  事件
         * @param eventListener 监听器
         */
        @SuppressWarnings({"rawtypes", "unchecked"})
        private void doInvokeListener(PrintEvent event, IListener eventListener){
            // 监听器类，被调用handleEvent方法。 对事件进行处理。
            try {
                eventListener.handleEvent(event);
            } catch (ClassCastException e) {
                // ignore
                // 事件不匹配，则会类型转化异常
                System.out.println("监听器 " + eventListener + " 对 " + event.eventType() + " 事件不感兴趣，忽略");
            }
        }

    }

}
