package my_demo.monitor.publishsubscribe;

import my_demo.monitor.publishsubscribe.publisher.IPublisher;
import my_demo.monitor.publishsubscribe.publisher.PublisherImpOne;
import my_demo.monitor.publishsubscribe.subcriber.ISubscriber;
import my_demo.monitor.publishsubscribe.subcriber.SubscriberImpOne;
import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 发布订阅模式
 * <p>
 * 与观察者模式最大的不同在于，加了个中间层 —————— 消息订阅器
 * <p>
 * 通知操作，不再由 被观察者 自己去做，而是委托给 订阅器 去做
 *
 *
 * 发布订阅模式中的回调思想：
 *
 * 订阅者  Subscriber 向  中间层订阅器 SubscribePublish   注册  update 方法
 * 未来的某个时刻
 * 由发布者 触发事件的发成，委托  中间层订阅器 SubscribePublish  回调  update 方法
 *
 * 注意：
 * 这里的 事件是由 上层模块 触发， 通知 则是由 中间层模块 执行。
 * 区别于 观察者模式，没有中间层 ，不仅由观察者  触发事件，观察者 还要负责 事件的通知。
 *
 *
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubPubTest {

    public static void main(String[] args) {

        SubscribePublish subscribePublish = new SubscribePublish("订阅器");

        ISubscriber<String> subscriber1 = new SubscriberImpOne<>("【订阅者1】");
        ISubscriber<String> subscriber2 = new SubscriberImpOne<>("【订阅者2】");

        // 将 订阅者 注册到 订阅器
        subscriber1.subscribe(subscribePublish);
        subscriber2.subscribe(subscribePublish);


        // 初始化 发布者
        IPublisher<String> publisher1 = new PublisherImpOne<>("【发布者1】");


        // 委托 订阅器  发布消息
        publisher1.publish(subscribePublish,
                "welcome", false);
        System.out.println("---------");


        publisher1.publish(subscribePublish,
                "to", false);
        System.out.println("---------");


        publisher1.publish(subscribePublish,
                "my zone", true);
        System.out.println("-----异步----");

    }

}