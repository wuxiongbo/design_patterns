package my_demo.monitor.event_jdk.listener.impl;

import my_demo.monitor.event_jdk.event.CloseEvent;
import my_demo.monitor.event_jdk.event.OpenEvent;
import my_demo.monitor.event_jdk.eventsource.WindowsEventSource;
import my_demo.monitor.event_jdk.listener.IListener;

/**
 * <p> 关窗 监听器 </p>
 *
 * 事件分发由 事件源处理
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/11
 * </pre>
 */
public class OpenWindowsListener implements IListener<OpenEvent> {

    @Override
    public void handleEvent(OpenEvent event) {
        System.out.println("OpenWindowsListener 监听到 openWindows事件，do open");
        IListener.super.handleEvent(event);
    }

    @Override
    public String toString() {
        return "OpenWindowsListener";
    }
}
