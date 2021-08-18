package my_demo.monitor.event.impl;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IListenerable;

/**
 * <p>事件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Event implements IEvent {
    private IListenerable eventSource;  //事件源(被观察者)
    private String methodname;          //事件源所执行的方法名称

    public Event(IListenerable eventSource, String methodname){
        super();
        this.eventSource = eventSource;
        this.methodname = methodname;
    }

    @Override
    public IListenerable getEventSource() {
        return eventSource;
    }

    //根据事件源所执行的不同的方法返回不同的事件类型
    @Override
    public String getEventType() {
        String eventType;

        if (methodname.startsWith("save")){
            eventType = Create_EVENT;
        }else if (methodname.startsWith("remove")){
            eventType = Delete_EVENT;
        }else if (methodname.startsWith("modify")){//修改
            eventType = Update_EVENT;
        }else if (methodname.startsWith("find")){
            eventType = Retrieve_EVENT;
        }else {
            eventType = "have not this event type";
        }

        return eventType;
    }
}

