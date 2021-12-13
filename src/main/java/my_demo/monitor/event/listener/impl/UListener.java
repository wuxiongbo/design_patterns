package my_demo.monitor.event.listener.impl;

import my_demo.monitor.event.listener.IListener;
import my_demo.monitor.event.event.IEvent;

/**
 * <p>监听器实现类(观察者)</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class UListener implements IListener {

    // 监听器将监听自己感兴趣的事件，这里只包含CRUD事件，其他事件不感兴趣。
    // 一旦感兴趣的事件在‘事件源’被触发或改变，‘监听器’ 立即得到 ‘事件源’ 的通知，做出响应
    @Override
    public void handleEvent(IEvent event) {
        String eventType = event.getEventType();

        if (IEvent.UPDATE_EVENT.equals(eventType)){
            System.out.println(" UListener 监听到并执行>>>>>>>'修改'操作");
        }

    }
}
