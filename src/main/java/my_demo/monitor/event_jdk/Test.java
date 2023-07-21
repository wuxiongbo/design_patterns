package my_demo.monitor.event_jdk;

import my_demo.monitor.event_jdk.event.OpenEvent;
import my_demo.monitor.event_jdk.eventsource.WindowsEventSource;
import my_demo.monitor.event_jdk.listener.impl.CloseWindowsListener;
import my_demo.monitor.event_jdk.listener.impl.OpenWindowsListener;
import my_demo.monitor.event_jdk.listener.impl.WindowsListener;

/**
 * <p> 观察者模式的变种---监听器 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {

    @org.junit.Test
    public void test1(){
        // 1）定义 ‘事件源’（被观察者）   窗口事件源
        WindowsEventSource windows = new WindowsEventSource();


        // 2）定义 ‘监听器’（观察者）     窗口事件监听器
        // 并为 事件源 注册 ‘监听器’。 以监听各个事件
        WindowsListener windowsListener = new WindowsListener();
//        windows.addWindowsListener(windowsListener);
        windowsListener.register(windows);


        // 3）触发开窗事件
        windows.doOpenWindows();
    }


    /**
     *  对 特定的事件 提供  特定的关注方法 和 特定的事件触发
     *  关注 关闭事件，实现 回调接口
     */
    @org.junit.Test
    public void test2(){
        // 1）事件源（被观察者）
        WindowsEventSource windows = new WindowsEventSource();


        // 2）监听器（观察者）:
        // 只对 关窗事件  感兴趣
        // 注册 监听器:
        // 具体这里是 调用事件源的 addCloseWindowListener方法。
        // 事件:
        // 在事件源中产生，注册的监听器 closeWindowsListener，只关注 关闭窗口事件
        CloseWindowsListener closeWindowsListener = new CloseWindowsListener();
//        windows.addWindowListener(closeWindowsListener);
        closeWindowsListener.register(windows);


        OpenWindowsListener openWindowsListener = new OpenWindowsListener();
//        windows.addWindowListener(openWindowsListener);
        openWindowsListener.register(windows);



        //3）事件源 触发 事件
        // 窗口关闭动作，产生 关闭事件。
        windows.doCloseWindows();

        // 窗口开启动作，产生 开启事件。
        windows.doOpenWindows();

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
