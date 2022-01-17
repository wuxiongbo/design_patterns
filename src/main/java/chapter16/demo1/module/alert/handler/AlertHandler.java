package chapter16.demo1.module.alert.handler;


import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.framework.ApiStatInfo;
import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.factory.factorymethod.INotificationFactory;
import chapter16.dependence.NotificationEmergencyLevel;
import chapter16.demo1.module.notify.v1.notification.Notification;
import chapter16.demo1.module.notify.v1.notification.factory.NotificationFactoryCreator;

/**
 * 告警处理器
 */
public abstract class AlertHandler {
    // 告警规则
    protected AlertRule rule;

    // 告警通知，支持邮件、短信、微信、手机等多种通知渠道。(告警紧急程度 与 通知渠道 对应的关系被写死)
    protected Notification notification;


    // 依赖注入：
    //      告警规则、
    //      告警通知的实现
    public AlertHandler(AlertRule rule,
                        NotificationEmergencyLevel notificationEmergencyLevel,
                        MsgSender msgSender) {

        this.rule = rule;


        //

        // 变化维度一：告警级别
        // 查表法： 通过 告警级别 查找 工厂方法
        INotificationFactory notificationFactory = NotificationFactoryCreator
                .getNotificationFactory(notificationEmergencyLevel);
        // 变化维度二：通知渠道
        // 通过工厂方法，创建通知类
        this.notification = notificationFactory.createNotification(msgSender);

    }

    public abstract void check(ApiStatInfo apiStatInfo);
}