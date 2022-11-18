package my_demo.monitor.publishsubscribe;

import my_demo.monitor.publishsubscribe.publisher.IPublisher;
import my_demo.monitor.publishsubscribe.publisher.PublisherImpOne;
import my_demo.monitor.publishsubscribe.subcriber.ISubscriber;
import my_demo.monitor.publishsubscribe.subcriber.SubscriberImpOne;
import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 发布订阅模式
 *
 * 与观察者模式最大的不同在于，加了个中间层。
 * 消息订阅器
 *
 * 通知操作不再由被观察者自己去做，而是委托给 订阅器 去做
 *
 *
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubPubTest {

    public static void main(String[] args) {

        SubscribePublish subscribePublish = new SubscribePublish("订阅器");

        ISubscriber<String> subscriber1 = new SubscriberImpOne<>("订阅者1");
        ISubscriber<String> subscriber2 = new SubscriberImpOne<>("订阅者2");

        // 注册订阅者
        subscriber1.subscribe(subscribePublish);
        subscriber2.subscribe(subscribePublish);


        // 初始化 发布者
        IPublisher<String> publisher1 = new PublisherImpOne<>("发布者1");
        // 委托给 订阅器 发布消息
        publisher1.publish(subscribePublish,
                "welcome", true);
        publisher1.publish(subscribePublish,
                "to", true);
        publisher1.publish(subscribePublish,
                "yy", false);
    }

}