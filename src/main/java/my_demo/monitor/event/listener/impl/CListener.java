package my_demo.monitor.event.listener.impl;


import my_demo.monitor.event.listener.IListener;
import my_demo.monitor.event.event.IEvent;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/22
 * </pre>
 */
public class CListener  implements IListener {
    @Override
    public void handleEvent(IEvent event) {
        String eventType = event.getEventType();
        if (IEvent.CREATE_EVENT.equals(eventType)){
            System.out.println(" CListener 监听到并执行>>>>>>>'添加'操作");
        }
    }
}
