package my_demo.monitor.publishsubscribe.v2;

/**
 *
 * reactivestreams  接口的简单实现
 *
 *
 * <a href="https://www.jianshu.com/p/d7fa576e50da"> 响应式编程入门之 Project Reactor </a>
 * @author Xander Wu
 * @date 2022/11/25 15:51
 */
public class Main {

    public static void main(String[] args) {

        // 初始化 发布者
        MyPublisher<String> publisher = new MyPublisher<>();

        // 初始化 订阅者
        MySubscriber subscriber = new MySubscriber();
        subscriber.next(new MySubscriber()).next(new MySubscriber());

        publisher.setMsg("hello world");

        // 1. Subscriber(订阅者)  主动 "订阅" (注册到)   Publisher(发布者)
        publisher.subscribe(subscriber);

    }

}
