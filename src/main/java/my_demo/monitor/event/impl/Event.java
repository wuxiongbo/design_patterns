package my_demo.monitor.event.impl;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IEventSource;

/**
 * <p>事件。 某种类型的新闻内容</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class Event implements IEvent {
    private IEventSource eventSource;   //事件源(被观察者)
    private String methodName;          //事件源所执行的方法名称

    public Event(IEventSource eventSource, String methodName){
        super();
        this.eventSource = eventSource;
        this.methodName = methodName;
    }

    @Override
    public IEventSource getEventSource() {
        return eventSource;
    }

    /**
     *  根据  ‘事件源’ 所执行的 ‘方法名’ 返回不同的 ‘事件类型’
     */
    @Override
    public String getEventType() {
        String eventType;

        if (methodName.startsWith("save")){
            eventType = CREATE_EVENT;
        }else if (methodName.startsWith("remove")){
            eventType = DELETE_EVENT;
        }else if (methodName.startsWith("modify")){//修改
            eventType = UPDATE_EVENT;
        }else if (methodName.startsWith("find")){
            eventType = RETRIEVE_EVENT;
        }else {
            eventType = "have not this event type";
        }

        return eventType;
    }
}

