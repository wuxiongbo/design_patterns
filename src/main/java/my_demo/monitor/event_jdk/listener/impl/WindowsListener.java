package my_demo.monitor.event_jdk.listener.impl;

import my_demo.monitor.event_jdk.event.PrintEvent;
import my_demo.monitor.event_jdk.listener.IListener;

/**
 * <p> 窗口 监听器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/11
 * </pre>
 */
public class WindowsListener implements IListener {

    @Override
    public void handleEvent(PrintEvent event) {

        // 拿感兴趣的事件，对事件响应。
        if(OPENWINDOWS.equals(event.toString())) {
            System.out.println("WindowsListener 监听到openWindows事件，do Open");
        }else if(CLOSEWINDOWS.equals(event.toString())){
            System.out.println("WindowsListener 监听到closeWindows事件，do Close");
        }

        event.doEvent();
    }
}
