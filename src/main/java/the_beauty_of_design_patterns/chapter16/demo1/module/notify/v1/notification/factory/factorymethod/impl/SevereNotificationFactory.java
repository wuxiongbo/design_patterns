package the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.impl;

import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.Notification;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.notification.impl.SevereNotification;

/**
 * <p>工厂方法</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class SevereNotificationFactory implements INotificationFactory {

    /**
     * @param msgSender
     * @return Notification   懒加载、多例
     */
    @Override
    public Notification createNotification(MsgSender msgSender) {
        return new SevereNotification(msgSender);
    }
}
