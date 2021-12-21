package chapter16.demo1.module.notify.v1.notification.impl;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.Notification;

/**
 * <p> 不同紧急程度的告警 实现 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class NormalNotification extends Notification {

    public NormalNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        // 3. 具体的通知操作，委托(桥接)给 MsgSender 来执行
        super.msgSender.send(message);
    }
}
