package the_beauty_of_design_patterns.chapter16.demo1.module.alert.handler.impl;


import the_beauty_of_design_patterns.chapter16.demo1.module.alert.AlertRule;
import the_beauty_of_design_patterns.chapter16.demo1.framework.ApiStatInfo;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.MsgSender;
import the_beauty_of_design_patterns.chapter16.dependence.NotificationEmergencyLevel;
import the_beauty_of_design_patterns.chapter16.demo1.module.alert.handler.AlertHandler;

/**
 * 新增的 TimeoutAlertHandler 类 属于扩展，符合开闭原则
 */
// 改动二：添加新的handler
public class TimeoutAlertHandler extends AlertHandler {

    public TimeoutAlertHandler(AlertRule rule,
                               NotificationEmergencyLevel notificationEmergencyLevel,
                               MsgSender msgSender) {

        super(rule,notificationEmergencyLevel, msgSender);

    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        //省略代码...
        // 接口请求时间超过 最大超时时间，触发告警
        if (
                // 超时时间
                apiStatInfo.getTimeoutCount() >

                // 查表法，根据 API 查找 告警规则
                rule.getMatchedRule(apiStatInfo.getApi())
                        .getMaxTime()) {

            // 通知接口的相关负责人或者团队
            notification.notify("接口请求超时");

        }

    }
}
