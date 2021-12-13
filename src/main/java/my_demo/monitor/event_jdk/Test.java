package my_demo.monitor.event_jdk;

import my_demo.monitor.event_jdk.event.PrintEvent;
import my_demo.monitor.event_jdk.eventsource.WindowsEventSource;
import my_demo.monitor.event_jdk.listener.impl.CloseWindowsListener;
import my_demo.monitor.event_jdk.listener.impl.WindowsListener;

import java.util.EventListener;

/**
 * <p> 观察者模式的变种---监听器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {
    public static void main(String[] args) {

        test1();
        System.out.println("===========================================");
        test2();

    }


    private static void test1(){
        //定义 事件源（被观察者）
        WindowsEventSource windows = new WindowsEventSource();

        //定义 监听器（观察者）
        EventListener windowsListener = new WindowsListener();

        //注册 监听器 到 事件源
        windows.addListener(windowsListener);


        /*
         * 事件源，触发事件：
         *   传入openWindows事件，通知所有的事件监听器
         *   对open事件感兴趣的listener将会执行
         */
        PrintEvent openWindowsEvent = new PrintEvent(windows, "openWindows");

        windows.notifyListenerEvents(openWindowsEvent);

    }


    /**
     *  对特定的事件提供特定的关注方法和事件触发
     *  关注关闭事件，实现回调接口
     */
    private static void test2(){
        // 事件源（被观察者）
        WindowsEventSource windows = new WindowsEventSource();

        // 监听器（观察者）    只对 关窗事件  感兴趣
        EventListener closeWindowsListener = new CloseWindowsListener();

        //注册 监听器。 具体这里是 调用addCloseWindowListener方法。 关注 关闭窗口事件
        windows.addCloseWindowListener(closeWindowsListener);

        //事件源 触发 窗口关闭动作。
        windows.doCloseWindows();

        //到了这里，是不是 类似按钮注册监听器，然后点击触发点击事件，执行监听器中对应事件的动作
    }

}

/*
    result:
    通知一个事件源 source: openWindows
    doOpen
    这就是事件监听模式
    回调接口类: MonitorListener
    回调函数接口: handleEvent
    通过回调模型，EventSource事件源便可回调具体监听器动作
*/
