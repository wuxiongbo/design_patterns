package my_demo.monitor.event_jdk.listener.impl;


import my_demo.monitor.event_jdk.event.PrintEvent;
import my_demo.monitor.event_jdk.listener.IListener;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/11
 * </pre>
 */
public class CloseWindowsListener implements IListener {

    @Override
    public void handleEvent(PrintEvent event) {

        // 处理感兴趣的事件。
        if (CLOSEWINDOWS.equals(event.toString())) {
            System.out.println("侦听到closeWindows事件，do close");
            event.doEvent();
        }

    }
}
