package chapter16.demo1.framework;


import chapter16.demo1.module.alert.Alert;
import chapter16.demo1.module.alert.AlertRule;
import chapter16.demo1.module.notify.v1.msgsender.impl.WechatMsgSender;
import chapter16.demo1.module.alert.handler.impl.ErrorAlertHandler;
import chapter16.demo1.module.alert.handler.impl.TimeoutAlertHandler;
import chapter16.demo1.module.alert.handler.impl.TpsAlertHandler;
import chapter16.dependence.NotificationEmergencyLevel;

/**
 * ApplicationContext 是一个单例类，
 * 负责  Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作。
 *
 */
public class ApplicationContext {

    private AlertRule alertRule;

    //    private Notification notification;

    private Alert alert;

    public void initializeBeans() {

        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
//        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码

        alert = new Alert();
        WechatMsgSender wechatMsgSender = new WechatMsgSender();
        // 改动三：注册handler
        alert.addAlertHandler(new TpsAlertHandler(alertRule, NotificationEmergencyLevel.URGENCY,wechatMsgSender));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule,NotificationEmergencyLevel.SEVERE,wechatMsgSender));
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule,NotificationEmergencyLevel.NORMAL,wechatMsgSender));

    }

    public Alert getAlert() {
        return alert;
    }

    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();

    // 构造方法中，对属性进行初始化
    private ApplicationContext() {
        initializeBeans();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }
}




