package chapter16.demo2;

import chapter16.demo1.bo.Notification;
import chapter16.demo2.interface1.MessageFormatter;
import chapter16.demo2.interface1.MessageQueue;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Demo {

    // 基于接口而非实现编程
    private MessageQueue msgQueue;

    // 依赖注入
    public Demo(MessageQueue msgQueue) {
        this.msgQueue = msgQueue;
    }


    // msgFormatter：多态、依赖注入
    public void sendNotification(Notification notification, MessageFormatter msgFormatter) {
        // ...
    }
}
