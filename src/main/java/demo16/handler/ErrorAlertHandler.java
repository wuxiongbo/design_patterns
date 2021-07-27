package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.Notification;
import demo16.bo.ApiStatInfo;
import demo16.bo.NotificationEmergencyLevel;

/**
 * 请求
 * @author Administrator
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        // 接口请求出错数大于某个最大允许值时，触发告警
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {

            // 通知接口的相关负责人或者团队
            notification.notify(NotificationEmergencyLevel.SEVERE, "超过最大错误请求数");

        }
    }
}
