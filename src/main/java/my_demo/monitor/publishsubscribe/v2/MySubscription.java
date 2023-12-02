package my_demo.monitor.publishsubscribe.v2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 *
 * 个人理解，Subscription有点像 责任链， Subscriber 有点像 链中单个的处理器
 * Publisher  将 各个操作，组装成了一个 流水线链条。 流水线处理好数据最终流转到 Subscriber
 *
 * @author Xander Wu
 * @date 2022/11/25 13:36
 */
public class MySubscription<T> implements Subscription {

    /**
     * 消息数据
     */
    private final T data;

    /**
     * 订阅者
     */
    private final Subscriber<? super T> actual;


    boolean isCanceled;

    public MySubscription(T data, Subscriber<? super T> actual) {
        this.data = data;
        this.actual = actual;
    }

    /**
     * 通知 '订阅者'
     * @param n – 向上游 Publisher '发布者' 请求的元素的严格正数
     */
    @Override
    public void request(long n) {

        if (!isCanceled) {
            try {

                // 4. Subscription(订阅器) 在 反向 接收到 '订阅者' 的调用后
                //    回调 Subscriber#onNext ，触发通知， 向下游 '订阅者' 传递 (通知) 消息
                actual.onNext(data);

                // 5. 在消息发布完成(链式调用)后，回调 Subscriber#onComplete ，结束本次流
                actual.onComplete();


            } catch (Exception e) {

                // 如果数据发布或者处理遇到错误，则 回调 Subscriber#onError
                actual.onError(e);

            }
        }
    }

    @Override
    public void cancel() {
        isCanceled = true;
    }


}

