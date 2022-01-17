package chapter16.demo1.module.alert.handler.impl;


import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.framework.ApiStatInfo;
import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.dependence.NotificationEmergencyLevel;

//import Notification;
import chapter16.demo1.module.alert.handler.AlertHandler;

/**
 * 请求
 * @author Administrator
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule,
                             NotificationEmergencyLevel notificationEmergencyLevel,
                             MsgSender msgSender) {

        super(rule,notificationEmergencyLevel,msgSender);

    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        // 接口请求出错数大于某个最大允许值时，触发告警
        if (
                // 累计错误次数
                apiStatInfo.getErrorCount() >

                // 查表法，根据 API 查找 告警规则
                rule.getMatchedRule(apiStatInfo.getApi())
                        .getMaxErrorCount()) {

            // 通知接口的相关负责人或者团队
            notification.notify("超过最大错误请求数");

        }
    }
}
