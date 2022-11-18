package my_demo.monitor.publishsubscribe.subcriber;

/**
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubscriberImpOne<Msg> implements ISubscriber<Msg> {
    public String name;

    public SubscriberImpOne(String name) {
        super();
        this.name = name;
    }

    @Override
    public void update(String publisher, Msg msg) {
        System.out.println(this.name + "收到" + publisher + "发来的消息:" + msg.toString());
    }

}