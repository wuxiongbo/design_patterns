package demo.handler;

import demo.AlertRule;
import demo.ApiStatInfo;
import demo.Notification;
import demo.NotificationEmergencyLevel;

public class TpsAlertHandler extends AlertHandler {
    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        // 当接口的 TPS 超过某个预先设置的最大值时,触发告警, 通知接口的相关负责人或者团队
        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
