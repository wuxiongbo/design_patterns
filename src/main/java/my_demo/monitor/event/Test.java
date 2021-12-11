package my_demo.monitor.event;

import my_demo.monitor.event.impl.CrudEventSource;
import my_demo.monitor.event.impl.listener.CRUDListener;
import my_demo.monitor.event.impl.listener.CListener;
import my_demo.monitor.event.impl.listener.DListener;
import my_demo.monitor.event.impl.listener.RListener;
import my_demo.monitor.event.impl.listener.UListener;
import my_demo.monitor.event.interfaces.IListener;

/**
 * <p> 监听器 的 简单实现</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {
    public static void main(String[] args) {
        // 事件源 (被观察者，‘事件’ 由 ‘事件源’ 发出)
        CrudEventSource eventSource = new CrudEventSource();
        // 监听者 (观察者)
        IListener crudListener = new CRUDListener();


        // 给 ‘事件源’ 注册 ‘监听器’
        eventSource.addListener(crudListener);



        // 触发save事件。   被观察者 创建相应事件 并通知 观察者
        eventSource.save();
        eventSource.remove();
        eventSource.modify();
        eventSource.find();


        System.out.println("================");

        // 给‘事件源’注册其他‘监听器’
        eventSource.addListener(new RListener());
        eventSource.addListener(new CListener());
        eventSource.addListener(new UListener());
        eventSource.addListener(new DListener());


        eventSource.save();
        eventSource.remove();
        eventSource.modify();
        eventSource.find();
    }
}

