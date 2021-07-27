package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.Notification;
import demo16.bo.ApiStatInfo;
import demo16.bo.NotificationEmergencyLevel;

// 改动二：添加新的handler
public class TimeoutAlertHandler extends AlertHandler {

    public TimeoutAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        //省略代码...
        // 接口请求时间超过 最大超时时间，触发告警
        if (apiStatInfo.getTimeoutCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTime()) {

            // 通知接口的相关负责人或者团队
            notification.notify(NotificationEmergencyLevel.SEVERE, "接口请求超时");

        }

    }
}
