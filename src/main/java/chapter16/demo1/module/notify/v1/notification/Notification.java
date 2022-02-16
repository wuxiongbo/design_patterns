package chapter16.demo1.module.notify.v1.notification;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;

/**
 * <p>通知类</p>
 *
 * 使用了
 *    “工厂方法模式”
 *    “桥接模式”
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public abstract class Notification {

    // 2. 利用 “组合” 关系，把 成员变量 作为 “桥梁”。
    // 说明：这么做的好处是，统一接口调用，这样就 可以随意切换 消息发送器的实现
    protected MsgSender msgSender;

    // 1. 通过构造方法，依赖注入  业务(消息发送器) 的"实现"
    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);

}
