package my_demo.monitor.event;

import my_demo.monitor.event.eventsource.impl.CrudEventSource;
import my_demo.monitor.event.listener.impl.CrudListener;
import my_demo.monitor.event.listener.impl.CListener;
import my_demo.monitor.event.listener.impl.DListener;
import my_demo.monitor.event.listener.impl.RListener;
import my_demo.monitor.event.listener.impl.UListener;
import my_demo.monitor.event.listener.IListener;

/**
 * <p> 观察者模式的变种---监听器 的 简单实现</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Test {
    public static void main(String[] args) {
        // 事件源 (充当 被观察者 的角色，‘事件’ 由 ‘事件源’ 发出)
        CrudEventSource eventSource = new CrudEventSource();
        // 监听者/监听器 (充当 观察者 的角色)
        IListener crudListener = new CrudListener();


        // 给 ‘事件源’ 注册 ‘监听器’
        eventSource.addListener(crudListener);



        // 触发 save事件。   被观察者(事件源)  触发并产生相应的事件  ，然后 通知 观察者(监听器)
        eventSource.save();
        eventSource.remove();
        eventSource.modify();
        eventSource.find();




        System.out.println("======================================================");



        // 给 ‘事件源’ 注册其他 ‘监听器’
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

