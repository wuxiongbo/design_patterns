package my_demo.monitor.event_jdk.listener.impl;

import my_demo.monitor.event_jdk.event.CloseEvent;
import my_demo.monitor.event_jdk.event.PrintEvent;
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
public class CloseWindowsListener implements IListener<CloseEvent> {

    @Override
    public void handleEvent(CloseEvent event) {

        // 处理感兴趣的事件。
//        if (CLOSEWINDOWS.equals(event.eventType())) {

        System.out.println("CloseWindowsListener 监听到closeWindows事件，do close");
        event.doEvent();

//        }

    }
}
