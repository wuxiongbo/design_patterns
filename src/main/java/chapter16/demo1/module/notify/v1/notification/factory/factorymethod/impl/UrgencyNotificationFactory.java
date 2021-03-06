package chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import chapter16.demo1.module.notify.v1.notification.Notification;
import chapter16.demo1.module.notify.v1.notification.impl.UrgencyNotification;

/**
 * <p>工厂方法</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class UrgencyNotificationFactory implements INotificationFactory {
    @Override
    public Notification createNotification(MsgSender msgSender) {
        return new UrgencyNotification(msgSender);
    }
}
