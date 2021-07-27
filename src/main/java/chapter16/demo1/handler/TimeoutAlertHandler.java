package chapter16.demo1.handler;


import chapter16.demo1.bo.AlertRule;
import chapter16.demo1.bo.ApiStatInfo;
import chapter16.demo1.bo.Notification;
import chapter16.demo1.bo.NotificationEmergencyLevel;

/**
 * 新增的 TimeoutAlertHandler 类 属于扩展，符合开闭原则
 */
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
