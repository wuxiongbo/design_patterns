package chapter16.demo1.module.alert.handler.impl;


import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.framework.ApiStatInfo;
import chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import chapter16.dependence.NotificationEmergencyLevel;
import chapter16.demo1.module.alert.handler.AlertHandler;

public class TpsAlertHandler extends AlertHandler {


    public TpsAlertHandler(AlertRule rule,
                           NotificationEmergencyLevel notificationEmergencyLevel,
                           MsgSender msgSender) {

        super(rule,notificationEmergencyLevel,msgSender);

    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        // 当接口的 TPS(每秒钟request数量) 超过某个预先设置的最大值时,触发告警
        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();


        if (
                // 每秒请求数(请求频率)
                tps >

                // 查表法，根据 API 查找 告警规则
                rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {

            // 通知接口的相关负责人或者团队
            notification.notify("TPS超过最大值");


        }
    }
}
