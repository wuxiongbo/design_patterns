package my_demo.monitor.event.impl.listener;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IListener;

/**
 * <p>监听器实现类(观察者。记者，只记录自己感兴趣的新闻类型)</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CRUDListener implements IListener {

    // 监听器将监听自己感兴趣的事件，这里只包含CRUD事件，其他事件不感兴趣。
    // 一旦感兴趣的事件在‘事件源’被触发或改变，‘监听器’ 立即得到 ‘事件源’ 的通知，做出响应
    @Override
    public void handleEvent(IEvent event) {
        String eventType = event.getEventType();

        if (IEvent.CREATE_EVENT.equals(eventType)){
            System.out.println(" CRUDListener 监听到并执行>>>>>>>'添加'操作");
        }
        else if (IEvent.DELETE_EVENT.equals(eventType)){
            System.out.println(" CRUDListener 监听到并执行>>>>>>>'删除'操作");
        }
        else if (IEvent.UPDATE_EVENT.equals(eventType)){
            System.out.println(" CRUDListener 监听到并执行>>>>>>>'修改'操作");
        }
        else if (IEvent.RETRIEVE_EVENT.equals(eventType)){
            System.out.println(" CRUDListener 监听到并执行>>>>>>>'查找'操作");
        }

    }
}
