package my_demo.monitor.publishsubscribe.v2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 *
 * 订阅者（观察者）
 *
 * @author Xander Wu
 * @date 2022/11/25 13:36
 */
public class MySubscriber implements Subscriber<String> {


    /**
     * 下游
     */
    private Subscriber actual;

    @Override
    public void onSubscribe(Subscription subscription) {

        // 3. '订阅者' Subscriber 委托 '订阅器' Subscription，
        // 调用 Subscription#request， 来 '通知' 订阅者自己 请求数据发布
        // 或者 Subscription#cancel，  来 '通知' 订阅者自己 取消数据发布
        //
        // 这就是响应式编程中的 '背压' ，'订阅者' Subscriber  可以 控制 数据发布（请求、取消 等）
        subscription.request(Long.MAX_VALUE);

    }


    @Override
    public void onNext(String data) {

        // ...处理数据
        System.out.println(data);


        // 传递给下一个订阅者
        if (actual != null) {
            actual.onNext(data);
        }

    }

    @Override
    public void onError(Throwable t) {
        // ...异常处理
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        // ...流结束处理
        System.out.println("complete");
    }
}