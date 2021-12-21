package chapter16.demo1.bo.v1.notification;

import chapter16.demo1.bo.v1.msgsender.MsgSender;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public abstract class Notification {

    // 2. 通过 “组合” 关系，实现 “桥接”
    protected MsgSender msgSender;

    // 1. 通过构造方法，依赖注入 "实现"
    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }


    public abstract void notify(String message);

}
