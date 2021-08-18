package my_demo.monitor.event.impl.listener;

import my_demo.monitor.event.interfaces.IEvent;
import my_demo.monitor.event.interfaces.IListener;

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

        if (IEvent.Update_EVENT.equals(eventType)){
            System.out.println("执行>>>>>>>修改操作");
        }

    }
}
