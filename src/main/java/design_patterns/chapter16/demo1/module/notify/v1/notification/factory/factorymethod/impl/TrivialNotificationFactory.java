package design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl;

import design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import design_patterns.chapter16.demo1.module.notify.v1.notification.impl.TrivialNotification;
import design_patterns.chapter16.demo1.module.notify.v1.notification.Notification;

/**
 * <p>工厂方法</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class TrivialNotificationFactory implements INotificationFactory {

    /**
     * @param msgSender
     * @return Notification   懒加载、多例
     */
    @Override
    public Notification createNotification(MsgSender msgSender) {
        return new TrivialNotification(msgSender);
    }

}
