package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.Notification;
import demo16.bo.ApiStatInfo;

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




