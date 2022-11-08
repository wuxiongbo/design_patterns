package my_demo.monitor.event_jdk.listener;

import my_demo.monitor.event_jdk.event.PrintEvent;

import java.util.EventListener;
import java.util.EventObject;

/**
 * <p>监听器接口</p>
 *
 * EventListener 是 所有事件监听器接口都必须扩展的标记接口。
 *
 * 关键要素：
 *  1.监听器标记
 *  2.事件处理器(能接受到事件源)
 *
 * 监听器的工作步骤：
 * 1.将监听器绑定到事件源。即，注册监听器
 * 2.事件发生后，触发监听器的成员方法。即，传递事件对象 给 事件处理器。
 * 3.事件处理器 通过 事件对象 获得 事件源，并对 事件源 进行处理。
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public interface IListener<E extends EventObject> extends EventListener {

    String OPENWINDOWS= "openWindows";
    String CLOSEWINDOWS= "closeWindows";

    // 事件处理器
    void handleEvent(E event);

}