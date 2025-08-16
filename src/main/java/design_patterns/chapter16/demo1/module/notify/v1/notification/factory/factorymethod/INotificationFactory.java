package design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod;

import design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import design_patterns.chapter16.demo1.module.notify.v1.notification.Notification;

/**
 * <p>工厂方法 抽象</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public interface INotificationFactory {

    Notification createNotification(MsgSender msgSender);

}
