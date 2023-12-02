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
    private Subscriber<?> actual;

    public MySubscriber next(MySubscriber actual){
        this.actual = actual;
        return actual;
    }

    @Override
    public void onSubscribe(Subscription subscription) {

        // 3. '订阅者' Subscriber 委托 '订阅器' Subscription，
        // 调用 Subscription#request， 来 '通知' 订阅者自己 请求数据发布
        // 或者 Subscription#cancel，  来 '通知' 订阅者自己 取消数据发布
        //
        // 这就是响应式编程中的 '背压' ，'订阅者' Subscriber  可以 控制 数据发布（请求、取消 等）
//        System.out.println("我是订阅者，我能现在能处理海量消息");
//        subscription.request(Long.MAX_VALUE);
        System.out.println("我是订阅者，我只处理一条消息消息");
        System.out.println();
        subscription.request(1L);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onNext(String msg) {

        // ...处理数据
        System.out.println( "订阅者 " + this + " 收到消息："+msg);


        // 传递给下一个订阅者
        if (actual != null) {
            System.out.println("并 传递消息给 " + actual);
            System.out.println();
            getActual(actual).onNext(msg + " + 消息接龙");
        } else {
            System.out.println("结束传递");
            System.out.println();
        }

    }

    private Subscriber getActual(Subscriber<?> actual){
        return actual;
    }


    @Override
    public void onError(Throwable t) {
        // ...异常处理
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        // ...流结束处理
        System.out.println(this +" 消息消费完毕：complete");
    }
}