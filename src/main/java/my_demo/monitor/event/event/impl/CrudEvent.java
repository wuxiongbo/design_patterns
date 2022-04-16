package my_demo.monitor.event.event.impl;

import my_demo.monitor.event.event.IEvent;
import my_demo.monitor.event.eventsource.IEventSource;

/**
 * <p>事件。 某种类型的新闻内容</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CrudEvent implements IEvent {

    private IEventSource eventSource;   // 事件源(被观察者)。 通常，事件中可以获取事件源，此示例中则没用到

    private String methodName;          // 事件源(被观察者) 所执行的方法名称。 用于判断事件类型

    public CrudEvent(IEventSource eventSource, String methodName){
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

