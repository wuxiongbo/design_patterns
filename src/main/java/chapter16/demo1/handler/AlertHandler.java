package chapter16.demo1.handler;


import chapter16.demo1.bo.AlertRule;
import chapter16.demo1.bo.ApiStatInfo;
import chapter16.demo1.bo.Notification;

/**
 * 告警处理器
 */
public abstract class AlertHandler {
    // 告警规则
    protected AlertRule rule;

    // 告警通知，支持邮件、短信、微信、手机等多种通知渠道
    protected Notification notification;



    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }


    public abstract void check(ApiStatInfo apiStatInfo);
}