package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.ApiStatInfo;
import demo16.bo.Notification;

public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
