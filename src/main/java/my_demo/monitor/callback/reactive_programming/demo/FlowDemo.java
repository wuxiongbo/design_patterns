package my_demo.monitor.callback.reactive_programming.demo;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * <p>响应流 Flow 的简单使用</p>
 * <p>
 * SubmissionPublisher 发布-订阅 框架 的基本使用
 *
 * <a href="https://www.jb51.net/article/240906.htm"> java9新特性 Reactive Stream 响应式编程 API </a>
 *
 * @author wuxiongbo
 * @date 2022/7/6 11:04
 */
public class FlowDemo {

    public static void main(String[] args) throws Exception {

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        Flow.Subscriber<Integer> subscriber = buildSubscriber();

        publisher.subscribe(subscriber);


        List.of(1, 2).forEach(publisher::submit);

        publisher.close();



        Thread.currentThread().join(10000L);
        System.out.println();
    }


    private static Flow.Subscriber<Integer> buildSubscriber(){
        return new Flow.Subscriber<>() {
            private int sum;

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("callback 1 保存订阅关系");

                this.subscription = subscription;
                this.subscription.request(1);

            }

            @Override
            public void onNext(Integer i) {
                System.out.println("callback 2 接受到数据: " + i);
                sum += i;

                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("a + b = " + sum);
            }
        };
    }

}
