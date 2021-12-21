package chapter16.demo1.module.alert.handler.impl;


import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.framework.ApiStatInfo;
import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.demo1.module.notify.v1.notification.NotificationEmergencyLevel;
import chapter16.demo1.module.alert.handler.AlertHandler;

/**
 * 新增的 TimeoutAlertHandler 类 属于扩展，符合开闭原则
 */
// 改动二：添加新的handler
public class TimeoutAlertHandler extends AlertHandler {

    public TimeoutAlertHandler(
            AlertRule rule,
            NotificationEmergencyLevel notificationEmergencyLevel,
            MsgSender msgSender
            /*, Notification notification*/) {
        super(rule,notificationEmergencyLevel, msgSender /*, notification*/);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        //省略代码...
        // 接口请求时间超过 最大超时时间，触发告警
        if (apiStatInfo.getTimeoutCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTime()) {
            // 通知接口的相关负责人或者团队
            notification.notify("接口请求超时");

        }

    }
}
