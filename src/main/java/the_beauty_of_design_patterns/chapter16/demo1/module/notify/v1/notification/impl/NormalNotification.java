package the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.impl;

import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.Notification;

/**
 * <p> 不同紧急程度的告警 实现 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class NormalNotification extends Notification {

    // 1. 通过构造方法，依赖注入 消息发送器的"实现"
    public NormalNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        // 3. 将具体的通知操作，“委托”(桥接) 给 消息发送器MsgSender 来执行
        super.msgSender.send(message);
    }
}
