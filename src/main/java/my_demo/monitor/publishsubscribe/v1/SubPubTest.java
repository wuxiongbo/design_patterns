package my_demo.monitor.publishsubscribe.v1;

import my_demo.monitor.publishsubscribe.v1.publisher.IPublisher;
import my_demo.monitor.publishsubscribe.v1.publisher.PublisherImpOne;
import my_demo.monitor.publishsubscribe.v1.subcriber.ISubscriber;
import my_demo.monitor.publishsubscribe.v1.subcriber.impl.SubscriberImpOne;
import my_demo.monitor.publishsubscribe.v1.subcriber.impl.SubscriberImpTwo;
import my_demo.monitor.publishsubscribe.v1.subpub.SubscribePublish;

/**
 * 发布、订阅 模式
 * <p>
 * 与 观察者模式 最大的不同在于，发布、订阅模式 加了个中间层 —————— 消息订阅器
 * <p>
 * 通知操作，不再由 被观察者 自己去做，而是委托给 订阅器 去做
 *
 *
 * 发布订阅模式中的回调思想：
 *
 * 订阅者 Subscriber  --向--》  中间层 订阅器 SubscribePublish   预先注册 订阅者的逻辑块，如 Subscriber.update() 方法，
 * 未来的某个时刻，由  发布者  Publisher  触发事件，
 * 发布者 Publisher ，委托(触发)  中间层 订阅器 SubscribePublish，
 * 订阅器 --回调--》之前注册 进来的订阅者的逻辑块 Subscriber.update() 方法，
 *
 * 注意：
 * 这里的 '事件' 是由 上层模块 Publisher 触发， '通知' 则是由 中间层模块 SubscribePublish 执行。
 * 区别于 观察者模式，观察者模式里面，没有中间层 ， 不仅由 观察者  触发事件，观察者 还要负责 事件的通知。
 *       发布订阅模式，职责则更加细化，将事件的 '触发' 与 '通知' 分给了两个类。
 *       这样的模式，更利于 消息（事件）的管理。
 *       例如，对消息的发布进行 性能更优的设计，如在 SubscribePublish 中引入 多线程（本示例的非阻塞模式中有体现）
 *
 *
 * 个人总结：
 * 中间层 就像一个容器。
 * 订阅者 观察 中间层。
 * 发布者 发布消息时，将产生的消息扔到容器中就不管了。
 * 中间层 监听到 发布者 扔消息过来了。 便开始 消费消息， 然后主动 通知（推送）给 订阅者。
 *
 *
 * 发布者 ----- 中间层 ---- 订阅者
 * 订阅者 只与  中间层   进行交互
 * 发布者 也只与 中间层  进行交互
 *
 *
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubPubTest {

    public static void main(String[] args) throws InterruptedException {

        // 1）初始化 "订阅器"
        SubscribePublish subscribePublish = new SubscribePublish("订阅器");


        // 初始化 多个 '订阅者'
        ISubscriber<String> subscriber1 = new SubscriberImpOne("【订阅者111】");
        ISubscriber<String> subscriber2 = new SubscriberImpOne("【订阅者222】");
        ISubscriber<Long> subscriber3 = new SubscriberImpTwo("【订阅者333】");


        // 2）将 多个 '订阅者' 注册到 "订阅器"
        subscriber1.subscribe(subscribePublish);
        subscriber2.subscribe(subscribePublish);
        subscriber3.subscribe(subscribePublish);



        // 初始化 '发布者1'
        IPublisher<String> publisher1 = new PublisherImpOne<>("【发布者11】");
        // 3）'发布者1' 将消息发布到  "订阅器"
        // 发布 String类型 消息 1
        publisher1.publish(subscribePublish, "welcome", true);
        System.out.println("---------");

        // 发布 String类型 消息 2
        publisher1.publish(subscribePublish, "to", true);
        System.out.println("---------");




        // 初始化 '发布者2'
        IPublisher<Long> publisher2 = new PublisherImpOne<>("【发布者22】");
        // 3）'发布者2'  将消息发布到  "订阅器"
        // 发布 Long类型 消息 3
        publisher2.publish(subscribePublish, 123L, true);



        System.out.println("---------");
        // 发布 Long类型 消息 4
        publisher1.publish(subscribePublish, "my zone", false);
        System.out.println("-----异步----");

        Thread.sleep(Integer.MAX_VALUE);
    }

}