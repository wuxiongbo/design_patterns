package my_demo.monitor.event;

import my_demo.monitor.event.impl.CRUDListener;
import my_demo.monitor.event.impl.CRUDListenerable;
import my_demo.monitor.event.impl.listener.CListener;
import my_demo.monitor.event.impl.listener.DListener;
import my_demo.monitor.event.impl.listener.RListener;
import my_demo.monitor.event.impl.listener.UListener;
import my_demo.monitor.event.interfaces.IListener;
import my_demo.monitor.event.interfaces.IListenerable;

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
        // 事件源 (被观察者)
        IListenerable listenerable = new CRUDListenerable();


        // 监听器 (观察者)
        IListener crudListener = new CRUDListener();
        // 给事件源 注册 监听器
        listenerable.setListener(crudListener);


        // 触发save事件。   被观察者 创建相应事件 并通知 观察者
        ((CRUDListenerable)listenerable).save();
        // 触发remove事件。 被观察者 创建相应事件 并通知 观察者
        ((CRUDListenerable)listenerable).remove();

        ((CRUDListenerable)listenerable).modify();

        ((CRUDListenerable)listenerable).find();

        listenerable.addListener(new RListener());
        listenerable.addListener(new CListener());
        listenerable.addListener(new UListener());
        listenerable.addListener(new DListener());


    }
}

