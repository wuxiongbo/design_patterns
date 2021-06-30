package demo.handler;

import demo.AlertRule;
import demo.ApiStatInfo;
import demo.Notification;

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
