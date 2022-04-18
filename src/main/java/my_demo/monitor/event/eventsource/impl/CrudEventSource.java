package my_demo.monitor.event.eventsource.impl;

import my_demo.monitor.event.event.impl.CrudEvent;
import my_demo.monitor.event.event.IEvent;
import my_demo.monitor.event.eventsource.IEventSource;
import my_demo.monitor.event.listener.IListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>事件源类(被观察者。媒体发言人，发布多种类型的新闻)</p>
 *
 * 事件处理模型:
 *      ‘组件’(事件源)  不处理自己的事件，而是将事件处理 “托付” 给外部的 ‘处理实体’（监听器）,
 *
 * 这样的  ‘事件处理模型’  称为 “‘事件’ 的 ‘授权处理’ 模型” 。
 * 好处是，不同的 ‘事件’ ，能够交由 不同类型的 ‘监听器’ 去处理。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CrudEventSource implements IEventSource {

    // 此列表，用来维护 各个监听器（即，观察者）
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
            listener.handleEvent(event);       // 回调 监听器
        }
    }


    /**
     * 事件源（被观察者） 只负责 “产生”并“通知” 事件
     */
    public void save(){
        System.out.println("CrudEventSource 产生‘插入’事件");
        IEvent saveEvent = new CrudEvent(this,"save");
        this.triggerListener(saveEvent);
    }
    public void remove(){
        System.out.println("CrudEventSource 产生‘删除’事件");
        IEvent removeEvent = new CrudEvent(this,"remove");
        this.triggerListener(removeEvent);
    }
    public void modify(){
        System.out.println("CrudEventSource 产生‘修改’事件");
        IEvent modifyEvent = new CrudEvent(this,"modify");
        this.triggerListener(modifyEvent);
    }
    public void find(){
        System.out.println("CrudEventSource 触发了‘查询’事件");
        IEvent findEvent = new CrudEvent(this,"find");
        this.triggerListener(findEvent);
    }

}
