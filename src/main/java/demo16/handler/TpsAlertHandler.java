package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.Notification;
import demo16.bo.ApiStatInfo;
import demo16.bo.NotificationEmergencyLevel;

public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        // 当接口的 TPS(每秒钟request数量) 超过某个预先设置的最大值时,触发告警
        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {

            //通知接口的相关负责人或者团队
            notification.notify(NotificationEmergencyLevel.URGENCY, "TPS超过最大值");

        }
    }
}
