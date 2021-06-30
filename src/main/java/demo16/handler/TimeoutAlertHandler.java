package demo16.handler;

import demo16.bo.AlertRule;
import demo16.bo.ApiStatInfo;
import demo16.bo.Notification;

// 改动二：添加新的handler
public class TimeoutAlertHandler extends AlertHandler {
    public TimeoutAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        //省略代码...
    }
}
