package the_beauty_of_design_patterns.chapter16.demo1.framework;


import the_beauty_of_design_patterns.chapter16.demo1.module.alert.Alert;
import the_beauty_of_design_patterns.chapter16.demo1.module.alert.AlertRule;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.impl.EmailMsgSender;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.impl.TelephoneMsgSender;
import the_beauty_of_design_patterns.chapter16.demo1.module.notify.v1.msgsender.impl.WechatMsgSender;
import the_beauty_of_design_patterns.chapter16.demo1.module.alert.handler.impl.ErrorAlertHandler;
import the_beauty_of_design_patterns.chapter16.demo1.module.alert.handler.impl.TimeoutAlertHandler;
import the_beauty_of_design_patterns.chapter16.demo1.module.alert.handler.impl.TpsAlertHandler;
import the_beauty_of_design_patterns.chapter16.dependence.NotificationEmergencyLevel;

import java.util.ArrayList;

/**
 * ApplicationContext 是一个单例类，
 * 负责
 *      1）Alert （责任链）的创建、
 *      2）组装依赖（alertRule 和 notification 的依赖注入）
 *      3）初始化（并添加 AlertHandler）工作。
 *
 */
public class ApplicationContext {

    private AlertRule alertRule;

    //    private Notification notification;

    // 告警链
    private Alert alert;

    public void initializeBeans() {

        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
//        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码

        alert = new Alert();


        WechatMsgSender wechatMsgSender = new WechatMsgSender(new ArrayList<>());
        TelephoneMsgSender telephoneMsgSender = new TelephoneMsgSender(new ArrayList<>());
        EmailMsgSender emailMsgSender = new EmailMsgSender(new ArrayList<>());


        // 改动三：注册handler
        alert.addAlertHandler(new TpsAlertHandler(
                alertRule, NotificationEmergencyLevel.URGENCY,wechatMsgSender));
        alert.addAlertHandler(new ErrorAlertHandler(
                alertRule,NotificationEmergencyLevel.SEVERE,telephoneMsgSender));
        alert.addAlertHandler(new TimeoutAlertHandler(
                alertRule,NotificationEmergencyLevel.NORMAL,emailMsgSender));

    }

    // 告警链
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




