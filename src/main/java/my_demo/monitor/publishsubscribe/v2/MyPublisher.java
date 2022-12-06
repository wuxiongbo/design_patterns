package my_demo.monitor.publishsubscribe.v2;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author Xander Wu
 * @date 2022/11/25 13:35
 */
public class MyPublisher<T> implements Publisher<T> {


    private T data;

    @Override
    public void subscribe(Subscriber subscriber) {


        // 初始化  subscription '订阅器'。 将 subscriber '订阅者'  注册给了 "订阅器"
        Subscription subscription = new MySubscription(data, subscriber);



        // 2. Publisher '发布者'   不自己通知  '订阅者'， 而是委托  "订阅器" 去做这件事，
        // 并且，"订阅器" 消息通知的 触发，也不是由 '发布者' 自己做，而是  交由 '订阅者'，
        // '订阅者' 实际上，则是  委托  传递过来的 "订阅器" 触发。
        // 区别于  传统的 发布订阅模式：
        //     是  '发布者' 委托 '发布器' 去 通知 '订阅者'。
        //     而这里，是 '订阅者'  委托  '发布器' 反过来通知自己。
        //     相当于 '发布者'  将数据发布的控制权  反向交给了 '订阅者'
        subscriber.onSubscribe(subscription);

    }

    public void setMsg(T data) {
        this.data = data;
    }
}
