package chapter16.demo1.module.notify.v1.notification.factory.factorymethod;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.Notification;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public interface INotificationFactory {
    Notification createNotification(MsgSender msgSender);


}
