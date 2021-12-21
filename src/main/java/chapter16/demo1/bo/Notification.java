package chapter16.demo1.bo;

import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * 告警通知类，支持邮件、短信、微信、手机等多种通知渠道
 *
 * Notification 类的代码实现有一个最明显的问题，那就是有很多 if-else 分支逻辑。
 *
 * 实际上，如果每个分支中的代码都不复杂，后期也没有无限膨胀的可能（增加更多 if-else 分支判断），那这样的设计问题并不大，
 * 没必要非得一定要摒弃 if-else 分支逻辑。
 *
 * 不过，Notification 的代码显然不符合这个条件。
 * 因为每个 if-else 分支中的代码逻辑都比较复杂，发送通知的所有逻辑都扎堆在 Notification 类中。
 * 我们知道，类的代码越多，就越难读懂，越难修改，维护的成本也就越高。
 *
 * 很多设计模式都是试图将庞大的类拆分成更细小的类，然后再通过某种更合理的结构组装在一起。
 *
 * 总结：业务“实现” 与 业务的“抽象” 耦合在了一起
 *
 * 这里所说的“抽象”，指的并非“抽象类”或“接口”，而是跟 具体的消息发送业务  无关的、被抽象出来的一套“类库”。
 * 这里所说的“实现”，也并非指“接口的实现类”，而是跟 具体的消息发送业务  相关的一套“类库”。
 *
 *
 *
 * 解决方式：
 *    桥接模式，通过 “组合”关系 “桥接” ， 以达到 将 “抽象” 与 “实现” 解耦 的目的
 *
 * 使用 桥接模式 设计思路，对代码进行重构：
 *    针对 Notification 的代码，我们将不同渠道的发送逻辑剥离出来，形成独立的消息发送类（MsgSender 相关类）。
 * 其中，Notification 类相当于"抽象"，MsgSender 类相当于"实现"，两者可以独立开发，通过组合关系（也就是桥梁）任意组合在一起。
 * 所谓 任意组合 的意思就是，不同紧急程度的消息和发送渠道之间的对应关系，不是在代码中固定写死的，我们可以动态地去指定（如，通过读取配置来获取对应关系）。
 *
 * MsgSender 和 Notification 独立开发，通过对象的 “组合关系”(桥梁) ，组装(桥接)在一起。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Notification {
    private List<String> emailAddresses;
    private List<String> telephones;
    private List<String> wechatIds;

    public Notification() {}

    public void setEmailAddress(List<String> emailAddress) {
        this.emailAddresses = emailAddress;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public void setWechatIds(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    /**
     * 通知
     * @param level   告警级别
     * @param msg     通知消息
     */
    public void notify(NotificationEmergencyLevel level, String msg){
        if (level.equals(NotificationEmergencyLevel.SEVERE)) {
            //...自动语音电话
        } else if (level.equals(NotificationEmergencyLevel.URGENCY)) {
            //...发微信
        } else if (level.equals(NotificationEmergencyLevel.NORMAL)) {
            //...发邮件
        } else if (level.equals(NotificationEmergencyLevel.TRIVIAL)) {
            //...发邮件
        }
    }

}