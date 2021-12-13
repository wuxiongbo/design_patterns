package my_demo.monitor.event.listener.impl;

import my_demo.monitor.event.listener.IListener;
import my_demo.monitor.event.event.IEvent;

/**
 * <p>监听器实现类(观察者。记者，只记录自己感兴趣的新闻类型)</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/16
 * </pre>
 */
public class CrudListener implements IListener {

    // 监听器将监听自己感兴趣的事件，这里只包含CRUD事件，其他事件不感兴趣。
    // 一旦感兴趣的事件在‘事件源’被触发或改变，‘监听器’ 立即得到 ‘事件源’ 的通知，做出响应
    @Override
    public void handleEvent(IEvent event) {
        switch (event.getEventType()){
            case IEvent.CREATE_EVENT:
                System.out.println(" CrudListener 监听到'添加'事件，执行'添加'操作");
                break;
            case IEvent.DELETE_EVENT:
                System.out.println(" CrudListener 监听到'删除'事件，执行'删除'操作");
                break;
            case IEvent.UPDATE_EVENT:
                System.out.println(" CrudListener 监听到'修改'事件，执行'修改'操作");
                break;
            case IEvent.RETRIEVE_EVENT:
                System.out.println(" CrudListener 监听到'查找'事件，执行'查找'操作");
                break;
            default:
                System.out.println("其他");
        }
    }
}
