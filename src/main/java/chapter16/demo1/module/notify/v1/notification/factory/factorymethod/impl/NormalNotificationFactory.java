package chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import chapter16.demo1.module.notify.v1.notification.Notification;
import chapter16.demo1.module.notify.v1.notification.impl.NormalNotification;

/**
 * <p>工厂方法</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class NormalNotificationFactory implements INotificationFactory {

    /**
     * @param msgSender
     * @return Notification   懒加载、多例
     */
    @Override
    public Notification createNotification(MsgSender msgSender) {
        return new NormalNotification(msgSender);
    }

}
