package chapter16.demo1.module.notify.v1.notification;

import chapter16.demo1.module.notify.v1.msgsender.MsgSender;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public abstract class Notification {

    // 2. 利用 “组合” 关系，作为 “桥梁”
    protected MsgSender msgSender;

    // 1. 通过构造方法，依赖注入 "实现"
    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }


    public abstract void notify(String message);

}
