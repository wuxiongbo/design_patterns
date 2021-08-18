package my_demo.monitor.event.impl;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IListener;
import my_demo.monitor.event.interfaces.IListenerable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>事件源类(被观察者)</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CRUDListenerable implements IListenerable {

    private IListener listener;

    private List<IListener> listeners = new ArrayList<>();


    @Override
    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    //注册监听器
    @Override
    public void setListener(IListener listener) {
        this.listener = listener;
    }

    //触发监听器
    @Override
    public void triggerListener(IEvent event) {
        listener.handleEvent(event);
    }

    public void save(){
        System.out.println("监听到了‘插入’事件");
        IEvent saveEvent = new Event(this,"save");
        this.triggerListener(saveEvent);
    }
    public void remove(){
        System.out.println("监听到了‘删除’事件");
        IEvent removeEvent = new Event(this,"remove");
        this.triggerListener(removeEvent);
    }
    public void modify(){
        System.out.println("监听到了‘修改’事件");
        IEvent modifyEvent = new Event(this,"modify");
        this.triggerListener(modifyEvent);
    }
    public void find(){
        System.out.println("监听到了‘查询’事件");
        IEvent findEvent = new Event(this,"find");
        this.triggerListener(findEvent);
    }

}
