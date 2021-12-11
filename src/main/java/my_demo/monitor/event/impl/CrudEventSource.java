package my_demo.monitor.event.impl;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IEventSource;
import my_demo.monitor.event.interfaces.IListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>事件源类(被观察者。媒体发言人，发布多种类型的新闻)</p>
 *
 * 事件处理模型:
 *
 *      组件(事件源)  不处理自己的事件，而是将事件处理托付给外部的 处理实体（监听器）,
 *
 *      这样的事件处理模型称为事件的 授权处理模型。
 *
 *      不同的事件，能够交由不同类型的监听器去处理。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CrudEventSource implements IEventSource {

    // 各个监听器（即，观察者）
    private List<IListener> listeners = new ArrayList<>();


    // 注册监听器
    @Override
    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    /**
     * 触发监听器 (即，通知“事件”，也就是 被观察者 通知 所有观察者)
     * 注意：事件处理 托付给了 监听器，事件源 本身 只‘产生’事件 而不 ‘处理’事件
     */
    @Override
    public void triggerListener(IEvent event) {
        for (IListener listener : listeners) {
            listener.handleEvent(event);
        }
    }


    /**
     * 事件源（被观察者） 只负责 “产生”并“通知” 事件
     */
    public void save(){
        System.out.println("CrudEventSource 产生‘插入’事件");
        IEvent saveEvent = new Event(this,"save");
        this.triggerListener(saveEvent);
    }
    public void remove(){
        System.out.println("CrudEventSource 产生‘删除’事件");
        IEvent removeEvent = new Event(this,"remove");
        this.triggerListener(removeEvent);
    }
    public void modify(){
        System.out.println("CrudEventSource 产生‘修改’事件");
        IEvent modifyEvent = new Event(this,"modify");
        this.triggerListener(modifyEvent);
    }
    public void find(){
        System.out.println("CrudEventSource 触发了‘查询’事件");
        IEvent findEvent = new Event(this,"find");
        this.triggerListener(findEvent);
    }

}
