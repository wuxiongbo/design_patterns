package chapter16.demo1.module.alert.handler;


import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.framework.ApiStatInfo;
import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import chapter16.demo1.module.notify.v1.notification.NotificationEmergencyLevel;
import chapter16.demo1.module.notify.v1.notification.Notification;
import chapter16.demo1.module.notify.v1.notification.factory.NotificationFactoryCreator;

//import Notification;

/**
 * 告警处理器
 */
public abstract class AlertHandler {
    // 告警规则
    protected AlertRule rule;

    // 告警通知，支持邮件、短信、微信、手机等多种通知渠道。(告警紧急程度 与 通知渠道 对应的关系被写死)
    protected Notification notification;

    public AlertHandler(AlertRule rule,
                        NotificationEmergencyLevel notificationEmergencyLevel,
                        MsgSender msgSender /*, Notification notification*/) {

        this.rule = rule;

        // 告警级别
        INotificationFactory notificationFactory = NotificationFactoryCreator
                .getNotificationFactory(notificationEmergencyLevel);

        // 通知渠道
        this.notification = notificationFactory.createNotification(msgSender);

    }

    public abstract void check(ApiStatInfo apiStatInfo);
}